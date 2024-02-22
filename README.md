# TeachMeSkills_C27_Lesson_14_HW
## Homework for lesson #14

### 1. **Task #1**

App allows user analyze `.txt` files with document numbers. During run app read `documentNumbers.txt` file or   
any other `.txt` file with path which user enter. App analyzes each line in `.txt` document and validate the value of  
the line. 

If the value matches next rules:
    1. Document number must be `15` characters long;
    2. Starts with the sequence `docnum` or `contract`;
    3. And contains only `letters or numbers` without any other chars (checking by regex);
then app write valid doc numbers to next `.txt` files: `validDocumentNumber.txt` or `validContractNumber.txt`.

If the value does not match rules above app writes this value to `invalidDocumentNumber.txt` files and append  
the reason why this value is not valid.

Once the app started `error_log.txt` and `execution_log.txt` files are created.  
    1. `execution_log.txt` contains all actions in the app from the very beginning.  
    2. `error_log.txt` contains all error which user can face. (e.g.: `String.format("File with path '%s' not found", path)`)

---

Application implemented by next classes and interfaces:
- Contants interfaces: **"ExceptionMessageConstants"**, **"FormatConstants"**, **"PathConstants"**.
- Exception class **"DocumentNumberFormatException"** with a constructor with message.
- Validator classes **"DocumentNumberValidator"** with document number validations according the rules above.
- Service classes **"AppService"**, **"WriteReportService"** & **"WriteFileService"** implement the whole app logic  
according the rules above. **"AppService"** starts and cloases app. **"WriteReportService"** create reports.  
**"WriteFileService"** implements full write/delete files logic.
- **"DocumentNumberValidator"** class validate values according rules above.
- Class **"Runner"** allows user to enter login, password and confirm password values.
