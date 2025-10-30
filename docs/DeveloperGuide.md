---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

<head>
    <h1 id="developerTitle">Developer Guide</h1>
</head>

<br>

<!-- * Table of Contents -->
<page-nav-print />

<h2 class="developerHeader">Acknowledgments</h2>

This project is adapted from the AddressBook-Level3 (AB3) foundation created for NUS’ CS2103T Software Engineering course.

--------------------------------------------------------------------------------------------------------------------

<h2 class="developerHeader">Setting up, getting started</h2>

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<h2 class="developerHeader">Design</h2>

<h3 class="developerHeader2">Architecture</h3>

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

<h3 class="developerHeader2">UI component</h3>

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<h3 class="developerHeader2">Logic component</h3>

**API** : [`Logic.java`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />


This sequence diagram illustrates a more complex interaction within the `Logic` component, taking `execute("filter ev/cycling")` API call as the example.

![Interactions Inside the Logic Component for the `filter ev/cycling` command](images/FilterSequenceDiagram.png)

<box type="info" seamless>
**Note:** The lifeline for `DeleteCommandParser` and `FilterCommandParser` should end at the destroy marker (X) 
but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to the LinkUp `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<h3 class="developerHeader2">Model component</h3>

**API** : [`Model.java`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/BetterModelClassDiagram.puml" width="600"></puml>

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<h3 class="developerHeader2">Storage component</h3>

**API** : [`Storage.java`](https://github.com/AY2526S1-CS2103T-F13-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

<h3 class="developerHeader2">Common classes</h3>

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

<h2 class="developerHeader">Documentation, logging, testing, configuration, dev-ops</h2>

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<h2 class="developerHeader">Appendix: Requirements</h2>

<h3 class="developerHeader2">Product scope</h3>

**Target user profile**:

* are small-scale leaders and organizers who need a lightweight but effective way to manage and access
  their contacts without relying on large enterprise tools
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Primary Users**:

* Community & Club Leaders (e.g., student societies, cultural clubs, sports teams)
* Non-Profit & Volunteer Leads (e.g., NGO project leads, charity group organizers)
* Small Organization / Business Team Leads (e.g., startups, tuition centers, hobby groups)

**User Persona**:

* Bryce is a university student studying a non-IT field. He is involved in many university clubs and leads some of them.
He needs to store and access many contacts easily, but does not require integrated communication features.

**Value proposition**:

* provides a lightweight and simple contact management tool
* manage contacts faster than a typical mouse/GUI driven app
* helps small organizations and student leaders organize their contacts more effectively than spreadsheets
* avoids unnecessary complexity while still supporting features like search, labeling, and grouping

<h3 class="developerHeader2">User stories</h3>

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​          | I want to …​                                              | So that I can…​                                                                      |
|----------|------------------|-----------------------------------------------------------|--------------------------------------------------------------------------------------|
| `* *`    | new user         | see usage instructions/help                               | refer to instructions when I forget how to use the App                               |
| `* * *`  | user             | add a contact                                             | record down people’s details                                                         |
| `* * *`  | user             | delete a contact                                          | remove unnecessary entries                                                           |
| `* * *`  | user             | view the contact list                                     | see all contacts                                                                     |
| `* *`    | user             | edit a contact                                            | update their details                                                                 |
| `* *`    | user             | search a contact by name                                  | find them quickly                                                                    |
| `* *`    | leader           | tag contacts with skills                                  | assign them to tasks                                                                 |
| `* *`    | leader           | add/remove skills from contacts                           | update contacts with their skills easily                                             |
| `* *`    | leader           | see event/role/telegram information to contacts           | know their context                                                                   |
| `* *`    | leader           | add/remove event/role information to contacts             | update contacts with their event/role easily                                         |
| `* *`    | leader           | filter people by any field                                | find the relevant contacts                                                           |
| `* *`    | leader           | add/remove favourite contacts and list favourite contacts | access key people easily                                                             |
| `* *`    | leader           | have clearer duplicate contact information                | understand which fields are duplicate more easily given the large number of contacts |
| `* *`    | experienced user | delete multiple contacts                                  | delete contacts faster after event                                                   |
| `* *`    | experienced user | use command shortcuts                                     | speed up workflow                                                                    |

<h3 class="developerHeader2">Use cases</h3>

(For all use cases below, the **System** is `LinkUp` and the **Actor** is the `user`, unless specified otherwise) <br>

**Use case: Add Contact**<br>

**MSS**
1. User requests to add contact.
2. LinkUp adds the contact and displays updated list of contacts. <br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    1a. LinkUp detects missing field information.
        1a1.LinkUp displays an error message.
        Use case ends.

    1b. Phone number and/or name and/or email already exist in LinkUp.
        1b1. LinkUp shows duplicate contact error message.
        Use case ends.

    1c. Invalid field format (E.g. usage of alphabets in phone number)
        1c1. LinkUp shows invalid input message.
        Use case ends.

    1d. Invalid command format.
        1d1. LinkUp shows error message.
        Use case ends.
</pre>

**Use case: Find Contact**<br>

**MSS**
1. User finds contact by name.
2. LinkUp displays list of matching contacts.<br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    1a. LinkUp finds no matching contacts.
        Use case ends.

    1b. Invalid command format.
        1b1. LinkUp shows error message.
        Use case ends.
</pre>

**Use case: Delete Contact**<br>

**MSS**

1.  User requests to list persons.
2.  LinkUp shows a list of persons.
3.  User requests to delete a specific person in the list.
4.  LinkUp deletes the person. <br>
    Use case ends.

**Extensions**

<pre class="no-bullets">
    2a. The list is empty.
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp shows an error message.
        Use case resumes at step 2.

    3b. Invalid command format.
        3b1. LinkUp shows error message.
         Use case resumes at step 2.
</pre>

**Use case: Mark Contact as Favourite**<br>

**MSS**
1. User requests to list persons.
2. LinkUp shows a list of persons.
3. User requests to mark a specific contact as favourite.
4. LinkUp marks the contact as favourite and displays confirmation message. <br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    2a. The list is empty.
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message.
        Use case resumes at step 2.

    3b. The contact is already marked as favourite.
        3b1. LinkUp displays a message indicating that the contact is already favourite.
        Use case resumes at step 2.

    3c. Invalid command format.
        3c1. LinkUp shows error message.
        Use case resumes at step 2.
</pre>

**Use case: Unmark Favourite Contact**<br>

**MSS**
1. User requests to list persons.
2. LinkUp shows a list of persons.
3. User requests to unmark a specific contact from favourites.
4. LinkUp unmarks the contact as favourite and displays confirmation message. <br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    2a. The list is empty.
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message.
        Use case resumes at step 2.

    3b. The contact is not as favourite.
        3b1. LinkUp displays a message indicating that the contact is not a favourite.
        Use case resumes at step 2.

    3c. Invalid command format.
        3c1. LinkUp shows error message.
        Use case resumes at step 2.
</pre>

**Use case: List Favourite Contacts**<br>

**MSS**
1. User requests to view all favourite contacts.
2. LinkUp displays a list of favourite contacts. <br>
    Use case ends. <br>

**Extensions**
<pre class="no-bullets">
    1a. There are no favourite contacts.
        1a1. LinkUp displays a message indicating that there are no favourites.
        Use case ends.

    1b. Invalid command format.
        1b1. LinkUp shows error message.
        Use case ends.
</pre>

**Use case: Filter Contacts**<br>

**MSS**
1. User requests to filter contacts by any field.
2. LinkUp displays contacts that match the filter condition. <br>
   Use case ends.
   
**Extensions**
<pre class="no-bullets">
    1a. No contacts match the filter condition.
        1a1. LinkUp displays an empty list.
        Use case ends.
   
    1b. Invalid command format.
        1b1. LinkUp shows error message.
        Use case ends.
</pre>

**Use case: Clear All Contacts**<br>

**MSS**
1. User requests to clear all contacts.
2. LinkUp deletes all contacts and displays an empty list. <br>
   Use case ends. <br>

**Use case: Add Skill to Contact** <br>

**MSS**
1. User requests to list persons.
2. LinkUp shows a list of persons.
3. User requests to add one or more skills to a specific contact.
4. LinkUp displays a success message and updates the contact’s skills. <br>
    Use case ends. <br>

**Extensions**
<pre class="no-bullets">
    2a. The list is empty.
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message.
        Use case resumes at step 2.

    3b. The skill to add already exists for the contact.
        3b1. LinkUp displays a error message indicating which skills are already present. 
        Use case resumes at step 2.

    3c. Invalid skill added to contact
        3c1. Linkup shows invalid skill error message. 
        Use case resumes at step 2.

    3d. Invalid command format.
        3d1. LinkUp shows an error message. 
        Use case resumes at step 2. 
</pre>

**Use case: Delete Skill from Contact** <br>

**MSS**
1. User requests to list persons.
2. LinkUp shows a list of persons.
3. User requests to remove one or more skills to a specific contact.
4. LinkUp displays a success message and updates the contact’s skills. <br>
   Use case ends. <br>

**Extensions**
<pre class="no-bullets">
    2a. The list is empty.
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message.
        Use case resumes at step 2.

    3b. The skill to delete does not exist for the contact.
        3b1. LinkUp displays a error message indicating which skills are missing. 
        Use case resumes at step 2.

    3c. Invalid skill added to contact
        3c1. Linkup shows invalid skill error message. 
        Use case resumes at step 2.

    3d. Invalid command format.
        3d1. LinkUp shows an error message. 
        Use case resumes at step 2. 
</pre>

**Use case: Copy Add Command string to Clipboard** <br>

**MSS**
1. User requests to list persons.
2. LinkUp shows a list of persons.
3. User requests to copy the add command string of a specific contact.
4. LinkUp copies the add command string of the specified contact to the clipboard.
5. LinkUp displays a success message indicating that the command has been copied. <br>
    Use case ends.

**Extensions**
<pre class="no-bullets">
    2a. The list is empty. 
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message. 
        Use case resumes at step 2.

    3b. Invalid command format.
        3b1. LinkUp shows an error message. 
        Use case resumes at step 2. 
</pre>

**Use case: Edit Contact**<br>

**MSS**
1. User requests to list persons.
2. LinkUp displays a list of persons.
3. User requests to edit a contact.
4. LinkUp edits the contact and displays the new information. <br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    2a. The list is empty. 
        Use case ends.

    3a. The given index is invalid.
        3a1. LinkUp displays an error message. 
        Use case resumes at step 2.

    3b. The user provides invalid or incomplete fields (e.g., invalid email or phone format).
        3b1. LinkUp displays an error message. 
        Use case resumes at step 2.

    3c. The user does not specify any field to edit. 
        3c1. LinkUp displays a message indicating that at least one field must be provided. 
        Use case resumes at step 2.

    3d. The edited information results in a duplicate contact.
        3d1. LinkUp displays a duplicate contact error message. 
        Use case resumes at step 2.

    3e. Invalid command format.
        3e1. LinkUp shows error message. 
        Use case ends.

    3f. The user edits only role or only event without the other field.
        3f1. LinkUp displays an error message.
        Use case resumes at step 2.

</pre>

**Use case: View Help**

**MSS**
1. User requests to view help information.
2. LinkUp displays example commands and a help window. <br>
   Use case ends.

**Extensions**
<pre class="no-bullets">
    1a. Invalid command format.
        1a1. LinkUp shows error message.
        Use case ends.
</pre>

**Use case: Exit LinkUp**<br>

**MSS**
1. User requests to exit the app.
2. LinkUp closes. <br>
   Use case ends.

<h3 class="developerHeader2">Non-Functional Requirements</h3>

1. **LinkUp** should work on any mainstream OS as long as it has Java 17 or above installed. 
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. **LinkUp** can store up to 1000 contacts without any noticeable difference in performance for typical usage.
4. **LinkUp** responds to your inputs within 2 seconds.
5. **LinkUp** is usable even if you're _non-tech savvy_ and have never used
   **LinkUp** before.
6. Data from **LinkUp** will not be able to be sent to other users easily through
   **LinkUp**.
7. **LinkUp** should validate all input data and show clear error messages if the input data is invalid.
8. **LinkUp** complies with _PDPA_ regulations in storing data.

<h3 class="developerHeader2">Glossary</h3>

1. Mainstream OS**: Windows, Linux, Unix, MacOS
2. Non tech-savvy: Someone who has low experience and skill in using technology
3. GUI: Graphical User Interface
4. CLI: Command Line Interface
4. JDK: Java Development Kit
5. PDPA: Personal Data Protection Agreement, data collection with consent
6. Organiser: A person who arranges an event or activity and has multiple contacts

--------------------------------------------------------------------------------------------------------------------

<h2 class="developerHeader">Appendix: Instructions for manual testing</h2>

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

<h3 class="developerHeader2">Launch and shutdown</h3>

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file <br>
   **Expected:** Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
   **Expected:** The most recent window size and location is retained.

3. Shutdown

    1. Press the close window button or type `exit`. <br>
    **Expected:** LinkUp will shut down instantly and save your contact data in your computer.

<h3 class="developerHeader2">Adding a person</h3>

Adding a person while all persons are being shown

1. **Test case:** `add n/John Doe p/98765432 e/johnd@example.com t/abc123 ev/Double or Nothing r/Orientation Camp Organiser`
   <br>`s/Cooking s/Python`<br>
   **Expected:** Contact is added to the list. Details of the new contact will be shown.

2. **Test case:** `add n/John Doe p/98765432 e/johnd@example.com t/abc123 ev/Double or Nothing r/Orientation Camp Organiser`
   <br>`s/Cooking s/Python`<br>
   **Expected:** Duplicate contact. No person is added. Error details shown in the status message.

3. **Test case:** `add n/John Doe e/johnd@example.com t/abc123 ev/Double or Nothing r/Orientation Camp Organiser`
   <br>`s/Cooking s/Python`<br>
   **Expected:** Invalid command format. No person is added. Error details shown in the status message.

4. **Other incorrect add commands to try:** `add`, `add n/John Doe p/^^^^^5432 e/johnd@example.com t/abc123 ev/Double or Nothing r/Orientation Camp Organiser`, `...` <br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Deleting a person</h3>

Deleting a person while all persons are being shown

1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `delete 1`<br>
   **Expected:** First contact is deleted from the list. Details of the deleted contact shown in the status message.

3. **Test case:** `delete 0`<br>
   **Expected:** Invalid index. No person is deleted. Error details shown in the status message.

4. **Other incorrect delete commands to try:** `delete`, `delete x`, `...` (where x is larger than the list size)<br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Editing a person</h3>

Editing a person while all persons are being shown

1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `edit 1 n/John`<br>
   **Expected:** First contact name is changed to `john`. Details of the deleted contact shown in the status message.

3. **Test case:** `edit 0 n/John`<br>
   **Expected:** Invalid index. No person is edited. Error details shown in the status message.

4. **Other incorrect edit commands to try:** `edit`, `edit 1 p/^^^`, `edit x`, `...` (where x is larger than the list size)<br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Filtering contacts</h3>

Filtering persons while all persons are being shown

1. **Test case:** `filter r/Organiser`<br>
       **Expected:** All contacts with the role as Organiser are listed.

2. **Test case:** `filter`<br>
   **Expected:** Invalid command. No person is listed. Error details shown in the status message.

<h3 class="developerHeader2">Finding a person by name</h3>

Finding a person while all persons are being shown

1. **Test case:** `find n/John`<br>
       **Expected:** All contacts with the name as John are listed.

2. **Test case:** `find`<br>
   **Expected:** Invalid command. No person is listed. Error details shown in the status message.

<h3 class="developerHeader2">Marking/Unmarking a person as favourite</h3>

Marking/Unmarking a person as favourite while all persons are being shown

1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `fav 1`<br>
   **Expected:** First contact is marked as favourite. A heart symbol appears beside the contact.

3. **Test case:** `fav 1` again <br>
  **Expected:** Message that says 1st contact is already marked as favourite is shown.

4. **Test case:** `unfav 0`<br>
   **Expected:** Invalid index. No person is unmarked from favourites. Error details shown in the status message.

5. **Other incorrect favourite commands to try:** `fav`, `fav x`, `...` (where x is larger than the list size)<br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Adding skills to a person</h3>

Adding skills to a person while all persons are being shown
1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `addskill 1 s/adobe s/python`<br>
   **Expected:** First contact is added the skill `adobe` and `python`.

3. **Test case:** `addskill 1 s/adobe` again <br>
   **Expected:** Message that says 1st contact already has the inputted skill.

4. **Test case:** `addskill 0 s/jokes`<br>
   **Expected:** Invalid index. No skill is added. Error details shown in the status message.
   
   1. **Test case:** `addskill 0 s/jok*91es `<br>
      **Expected:** Invalid skill error message is shown, skills should be alphanumeric with no spacing.
   
   2. **Other incorrect favourite commands to try:** `addskill`, `addskill 1 s/9%aggss ejd`, `addskill x s/fight`
   (where x is larger than the list size)<br>
     **Expected:** Similar to previous.

<h3 class="developerHeader2">Deleting skills from a person</h3>

Adding skills to a person while all persons are being shown
1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `dskill 1 s/adobe`<br>
   **Expected:** First contact removed of the skill `adobe`.
   
3. **Test case:** `dskill 1 s/adobe` again <br>
  **Expected:** Message that says the contact does not have the inputted skill.

4. **Test case:** `dskill 0 s/jokes`<br>
   **Expected:** Invalid index. No skill is removed. Error details shown in the status message.

5. **Test case:** `dskill 0 s/jok*91es`<br>
   **Expected:** Invalid skill error message is shown, skills should be alphanumeric with no spacing.

6. **Other incorrect favourite commands to try:** `dskill`, `dskill 1 s/9%aggss hi`, `dskill x s/fight`
   (where x is larger than the list size)<br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Copy</h3>

Copy the command of the person while all persons are being shown
1. **Prerequisites:** List all persons using the `list` command. Multiple persons in the list.

2. **Test case:** `copy 1 s`<br>
   **Expected:** Add command string of the first contact is copied to the clipboard. 
    Success message shown in the result display box.

3. **Test case:** `copy 0`<br>
   **Expected:** Invalid index. No command is copied. Error details shown in the status message.

4. **Other incorrect favourite commands to try:** `copy abc`, `copy s/9%aggss`, `copy x`
   (where x is larger than the list size)<br>
   **Expected:** Similar to previous.

<h3 class="developerHeader2">Saving data</h3>


1. **Simulate saving data** 
   1. Launch LinkUp. 
   2. Add/delete/edit a contact. 
   3. Close LinkUp. 
   4. Relaunch LinkUp. <br>
  **Expected:** The edited contact list will be restored when you relaunch LinkUp.<br><br>

2. **Simulate a missing file** 
   1. Close LinkUp. 
   2. Head to the `data` folder of LinkUp. 
   3. Delete the `addressbook.json` file. 
   4. Relaunch LinkUp. <br>
  **Expected:** LinkUp loads and lists sample contacts.<br><br>

3. **Simulate a corrupted file** 
   1. Close LinkUp. 
   2. Head to the `data` folder of LinkUp. 
   3. Edit the contents of the `addressbook.json` file and add invalid characters (eg. $%^#). 
   4. Relaunch LinkUp. <br>
  **Expected:** LinkUp's contact list will be empty.<br><br>


