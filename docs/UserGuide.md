---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# User Guide

Meet **LinkUp**, a desktop app designed to help team leaders manage their contacts more efficiently and effectively.
Optimized for users who prefer typing commands to use systems, **LinkUp** helps you manage your contacts without 
ever taking your hands off your keyboard.

--------------------------------------------------------------------------------------------------------------------
## **About LinkUp**
### Target Users
This app is tailored for **community and club leaders** responsible for:
* Tracking members' events and roles
* Recording members' skills to efficiently allocate work
* Communicating with members via messaging platforms (e.g. Telegram)

### What do you need?
**Basic Digital Literacy:**
* You should know how to download software and type commands
* You should have experience with keyboard-driven workflows to tackle tasks efficiently

**Technical Requirements:**
* A Mac, Windows, or Linux computer
* 50MB of available storage space

### Where do you start?
* **New users:** Start from [_Quick Start_](#quick-start) to set up LinkUp on your computer
* **Existing users:** Go to [_Features_](#features) for command details
* **Advanced users:** Refer to the [_Command Summary_](#command-summary) for quick command guide

--------------------------------------------------------------------------------------------------------------------

## **Quick Start**

Let's walk you through the setup process of LinkUp.

1. **Prerequisites:** <br>
   You are required to install a specific version of the Java Development Kit (JDK) to run **LinkUp**. A JDK is a software package that provides the tools needed to develop and run Java applications.
    * **Mac users:** Follow the JDK installation guide [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
    * **Windows users:** Follow the JDK installation guide [here](https://se-education.org/guides/tutorials/javaInstallationWindows.html).
    * **Linux users:** Follow the JDK installation guide [here](https://se-education.org/guides/tutorials/javaInstallationLinux.html).

<box type="tip" seamless> 
    <b>Don't know your OS?</b> Click <a href="https://whatsmyos.com/" target="_blank" rel="noopener noreferrer">here </a> to check.
</box>

2. **Download LinkUp:** <br>
   To install the latest `LinkUp.jar` file:
   1. Go to the [LinkUp Releases Page](https://github.com/AY2526S1-CS2103T-F13-2/tp/releases).
   2. Scroll down the latest release section until you see a section an <b>Assets</b> header, which looks like this:
   <img src="images/LinkUpAssets.jpg" width="650px">
   3. Click on the file that ends with ".jar" to automatically download the latest `LinkUp.jar` file. 

<br>

3. **Move It:** <br>
   Place the `LinkUp.jar` file in your preferred folder in your computer.

4. **Launch It**: <br>
    1. Open the command terminal of your computer
       * **Mac users:** Open Terminal via Spotlight (⌘ + Space → type _Terminal_ → press _return_ ).
       * **Windows users:** Open Command Prompt by: Press Win + R → type cmd → press _Enter_
       * **Linux users:** Open Terminal via Ctrl + Alt + T or search for it in your application menu.

       <br>

    2. Navigate into the folder you put the `LinkUp.jar` file in.
       *  You can do this by using the change directory (`cd`) function. For example, if `LinkUp.jar` is in the Downloads folder of your computer,
          type `cd Downloads` in the command terminal to navigate into the Downloads folder.

       <br>

    3. Type `java -jar LinkUp.jar` command to run the application.
    4. In a few seconds, the LinkUp interface will appear. 
   Scroll down for [_Interface Walkthrough_](#interface-walkthrough).
    5. Use It: <br>
   Now you can start using **LinkUp**. Refer to the [_Features_](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Interface Walkthrough**

1. **Menu Bar:** 
2. **Command Box:**
3. **Result Box:**
4. **Contact List:**

--------------------------------------------------------------------------------------------------------------------

## **Features**

<box type="info" seamless>

**Notes about the command format:**<br>

* **Words in uppercase are information you need to provide.**<br>
  e.g. For `add n/NAME`, replace `NAME` with an actual name.
  * You can type in `add n/John Doe` or `add n/Michael Jordan` etc.
  
  <br>

* **Items in square brackets are optional.**<br>
  e.g. In `n/NAME [s/SKILL]`, `s/SKILL` is optional.
  * You can type `n/John Doe s/Java` or `n/John Doe`.

  <br>

* **Items with `…`​ after them can be used multiple times (including not at all).**<br>
  e.g. `[s/SKILL]…​` means you can add 0 skills, 1 skill, or many.
  * You can type ` ` (i.e. 0 times), `s/Java`, or `s/Photography s/Cooking` etc.

  <br>
  
* **Fields can be in any order.**<br>
  e.g. If a command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* **Extra parameters for commands that do not take in parameters** (such as `help`, `list`, `exit` and `clear`) **will be ignored.**<br>
  e.g. If you type in `help 123`, it will be treated as just `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as
  space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Help & Exit
#### View Help

View a brief message on commands you can use and a pop-up message explaining how to access the help page.

**Format**: <br> `help` or `h`

**Expected Output:** <br>
![Example Output for Help Command](images/Help Output.png) <br>

#### Close LinkUp

Exit the application.

**Format:** <br> `exit`

The Graphic User Interface (GUI) will close after the input. LinkUp saves your contact list automatically in your computer.
This means that your contact list will be restored whenever you reopen LinkUp.

<br>

### Contact Management
#### Add a Contact

Add a person to the address book.

**Format:** <br> `add n/NAME p/PHONE_NUMBER e/EMAIL t/TELEGRAM_HANDLE ev/EVENT r/ROLE [s/SKILL]…​` or <br>
`a n/NAME p/PHONE_NUMBER e/EMAIL t/TELEGRAM ev/EVENT r/ROLE [s/SKILL]…​`

**Parameters:** <br>
`n/NAME`: Name of your contact. <br>
`p/PHONE_NUMBER`: Phone number of your contact. It should only contain numbers, and it should be at least 3 digits long <br>
`e/EMAIL`: Email address of your contact. It should be of the format local-part@domain. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
This is followed by a '@' and then a domain name. The domain name must end with a domain label at least 2 characters long, have each domain label start and end with alphanumeric characters, and have each domain label consist of alphanumeric characters, separated only by hyphens, if any.<br>
`t/TELEGRAM_HANDLE`: Telegram handle of your contact. It should be English letters (a-z, A-Z), digits (0-9), and underscores. It also has to be between 5 and 32 characters long. <br>
`ev/EVENT`: Event of your contact. It can take up any values and should not be blank. <br>
`r/ROLE`: Role of your contact. It can take up any values and should not be blank. <br>
`s/SKILL`: Skill of your contact. It should be alphanumeric with no spacing. <br>

<box type="tip" seamless>
A person can have any number of skills (including 0)
</box>

**Example Input:** <br>
`add n/John Deez p/98765431 ev/Supernova r/Organiser e/johnde@example.com t/john123`

**Expected Output:** <br>
![Example Output for Add Command](images/AddCommandExampleOutput.png) <br>

<box type="tip" seamless>
Tip: The order of fields do not matter.
</box>

<br>

#### Edit a Contact

Edit the details of an existing person in the address book.

**Format**: <br> `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TELEGRAM_HANDLE] [ev/EVENT] [r/ROLE] [s/SKILL]…​` or <br>
`e INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TELEGRAM_HANDLE] [ev/EVENT] [r/ROLE] [s/SKILL]…​`

* Edit the details of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* When editing skills, the existing skills of the person will be removed and replaced with your skills input i.e adding of skills is not cumulative.
* You can remove a person’s skills by typing `s/` without specifying any skills after it.

**Example Input:** <br>
`edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.

**Expected Output:** <br>
`Edited Person: John Deez; Phone: 91234567; Telegram: john123; Email: johndoe@example.com; Role: Organiser; Event: Supernova; Skills:`

**Example Input:** <br>
`e 2 n/Betsy Crower s/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing skills.

**Expected Output:** <br>
`Edited Person: Betsy Crower; Phone: 1234567; Telegram: crownie; Email: betsycrowe@example.com; Role: Organiser; Event: Supernova; Skills:`

<br>

#### Delete Contacts

Delete specified persons from the address book.

**Format:** <br> `delete INDEX` or `d INDEX`

* Delete the person at the specified `INDEX`.
* At least one `INDEX` must be provided.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

**Example Input:** <br>
`list` followed by `delete 1 2` deletes both the 1st and 2nd persons in the address book.

**Expected Output:** <br>
`Deleted Person: John Deez; Phone: 91234567; Telegram: john123; Email: johndoe@example.com; Role: Organiser; Event: Supernova; Skills:, Betsy Crower; Phone: 1234567; Telegram: crownie; Email: betsycrowe@example.com; Role: Organiser; Event: Supernova; Skills:`

**Example Input:** <br>
`find Betsy` followed by `d 1` deletes the 1st person in the results of the `find` command.

**Expected Output:** <br>
`Deleted Person: Betsy Crower; Phone: 1234567; Telegram: crownie; Email: betsycrowe@example.com; Role: Organiser; Event: Supernova; Skills:`

#### Copy Contact


<br>

#### Delete ALL Contacts

Clear all persons from the address book.

**Format:** <br> `clear`

**Expected Output:** <br>
`Address book has been cleared!`

<br>

### Contact Viewing & Searching

#### List all Contacts

View a list of all persons in the address book.

**Format**: <br> `list` or `l`

<br>

#### Search for Contacts by Name

Find persons whose names contain any of the given keywords.

**Format:** <br> `find KEYWORD [MORE_KEYWORDS]` or `f KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched. e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned.
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

**Example Input:** <br>
`find alex david` or `f alex david` <br>

**Expected Output:** <br>
If there are Alex Yeoh and David Li in your contacts, they will be listed.

<br>

#### Filter Contacts by any Fields

Filter contacts from the address book based on name, event, role, phone number, telegram, email and skills.

**Format:** <br> `filter [n/KEYWORD] [t/KEYWORD] [ev/KEYWORD] [r/KEYWORD] [s/KEYWORD]` or <br> 
`fil [n/KEYWORD] [t/KEYWORD] [ev/KEYWORD] [r/KEYWORD] [s/KEYWORD]`

* Filter the address book based on specified `KEYWORD`.
* Filter and produces all possible outputs matching at least one `KEYWORD` from each field.
* At least one of the optional fields must be provided.
* Can filter many words of a field by using space. e.g. `n/Alice Bob` will match all people have `Alice` or `Bob` in their name
* The keyword is case-insensitive.
  * e.g. `filter ev/Supernova` is the same as `filter ev/supernova`.
* The keyword can be a part of the full word.
  * e.g. `filter n/Ali` will filter out a person named `Alice` as well!
* The keyword **can be any valid character** 1, a, @, …​
* If more than one field is provided, LinkUp will filter contacts that match **all** the fields provided.

**Example Input:** <br>
Using the sample AddressBook below:

![Example AddressBook for Filter Command](images/FilterCommandExampleInput.png)

Typing the command `filter ev/charity gala` will show:

![Example Output 1 for Filter Command](images/FilterCommandExampleOutput1.png)

All of your contacts with `Charity Gala` in their event will be listed out. 

Another example command `filter n/Mike ev/Charity` will show:

![Example Output 2 for Filter Command](images/FilterCommandExampleOutput2.png)

This time, only contacts with both `Mike` in their name and `Charity` in their event will be listed.

<br>

### Skill Management
#### Add new skill


<br>

#### Delete existing Skill

<br>

### Favourite Contact Management
#### Add a Contact to Favourites

Add a specified person from the address book to favourites.

**Format:** <br> `fav INDEX`

* Add the person at the specified `INDEX` to favourites.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

**Example Input:** <br>
`list` followed by `fav 1` adds the 1st person in the address book to favourites.

**Expected Output:** <br>
`Marked John Deez as favourite.`

**Example Input:** <br>
`find Betsy` followed by `fav 1` adds the 1st person in the results of the `find` command to favourites.

**Expected Output:** <br>
`Marked Betsy Crower as favourite.`

<br>

#### Remove a Contact from Favourites

Remove a specified person from the address book from favourites.

**Format:** <br> `unfav INDEX`

* Remove the person at the specified `INDEX` from favourites.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

**Example Input:** <br>
`list` followed by `unfav 1` removes the 1st person in the address book from favourites.

**Expected Output** <br>
`Unmarked John Deez from favourites.`

**Example Input:** <br>
`find Betsy` followed by `unfav 1` removes the 1st person in the results of the `find` command from favourites.

**Expected Output:** <br>
`Unmarked Betsy Crower from favourites.`

<br>

#### List all Favourite Contacts

View a list of all your favourite persons in the address book.

**Format:** <br> `lfav`

<br>

### Data Storage
#### Save the Data

LinkUp data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

#### Edit the Data File

LinkUp data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>
**Caution:**
If your changes to the data file makes its format invalid, LinkUp will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause LinkUp to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------
## **Command Summary**

Action | Command
--------|------------------
**Add**: <br> Add a contact | `add n/NAME p/PHONE_NUMBER e/EMAIL t/TELEGRAM_HANDLE ev/EVENT r/ROLE [s/SKILL]…​` <br> <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com t/james0336 s/Python s/Java`
**Clear**: <br> Delete all contacts | `clear`
**Delete**: <br> Delete contacts | `delete INDEX`<br> <br> e.g. `delete 3`
**Edit**: <br> Edit a contact | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TELEGRAM_HANDLE] [ev/EVENT] [r/ROLE] [s/SKILL]…​`<br> <br> e.g.`edit 2 n/James Lee e/jameslee@example.com`
**Find**: <br> Search for a contact by name | `find KEYWORD [MORE_KEYWORDS]`<br> <br> e.g. `find James Jake`
**Filter**: <br> Filter contacts by any fields | `filter [n/KEYWORD] [t/KEYWORD] [ev/KEYWORD] [r/KEYWORD] [s/KEYWORD]`<br> <br> e.g. `filter ev/Hunt`
**List**: <br> List all contacts | `list`
**Favourite**: <br> Add a contact to favourites | `fav INDEX`<br> <br> e.g. `fav 3`
**Unfavourite**: <br> Remove a contact from favourites | `unfav INDEX`<br> <br> e.g. `unfav 3`
**List favourites**: <br> List all favourite contacts | `lfav`
**Help**: <br> View a brief message on commands you can use | `help`
**Exit**: <br> Exit LinkUp | `exit`

--------------------------------------------------------------------------------------------------------------------

## **Troubleshooting**
### Frequently Asked Questions

**Q**: How do I transfer my data to another computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LinkUp home folder.

**Q**: How do I edit only 1 of my 2 skills for a specific contact? <br>
**A**: You would have to edit both skills in order for you to see the changes. So assuming
you have `skills: Boxing, Drumming` but you wish to change Drumming to Singing, you can only do this by using the `edit` command
and typing `edit [INDEX] s/Boxing s/Drumming` or `e [INDEX] s/Boxing s/Drumming`.

**Q**: Why am I unable to run the `LinkUp.jar` file? <br>
**A**: Ensure you have Java `17` or above installed in your computer. You can type `java -version` in your command terminal
of your computer to check.
* For Mac users, follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)
ensure you have the precise JDK version prescribed.
* For Windows users, follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationWindows.html)
ensure you have the precise JDK version prescribed.
* For Linux users, follow the setup guide [here](https://se-education.org/guides/tutorials/javaInstallationLinux.html)
ensure you have the precise JDK version prescribed.

Then, open the command terminal of your computer, and navigate into the folder you put the `LinkUp.jar` file in.
You can do this by using the `cd` function. For example, if `LinkUp.jar` is in the Downloads folder of your computer,
type `cd Downloads` in the command terminal to navigate into the Downloads folder.
Finally, type `java -jar LinkUp.jar` command to run the application. <br>

### Known Issues

* **Issue:** When using multiple screens, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. <br>
**Solution:** Delete the `preferences.json` file created by the application before running the application again.
* **Issue:** If you minimize the Help Window and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. <br>
**Solution:** Manually restore the minimized Help Window.

### Common Error Messages

### Further Help & Support
* Visit our [GitHub Repository](https://github.com/AY2526S1-CS2103T-F13-2/tp) or [Developer Guide](https://ay2526s1-cs2103t-f13-2.github.io/tp/DeveloperGuide.html) for technical details.
* Post your query [here](https://github.com/AY2526S1-CS2103T-F13-2/tp/issues) by creating a new issue, detailing 
  * Your exact command input
  * Errors faced
  * Your computer's operating system
  * Your JDK Version 
  * Screenshots <br>
We will get back to you as soon as possible.

--------------------------------------------------------------------------------------------------------------------
## **Get in Touch with Us**
We’d love to hear from you! Whether you have questions, feedback, or suggestions for new features, feel free to reach out to us.

**Who We Are:** <br>
We are the AY2526S1-CS2103T-F13-2 team of developers passionate about creating efficient and user-friendly tools.

**Contact Information:** <br>
📧 Email: e1408873@u.nus.edu <br>
💬 GitHub Issue Page: [LinkUp Issues](https://github.com/AY2526S1-CS2103T-F13-2/tp/issues)

**Feature Suggestions:** <br>
Have an idea to make LinkUp even better? Post it in our GitHub Issues page or email us — we’re always looking to improve!
