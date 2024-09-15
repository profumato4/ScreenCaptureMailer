# ScreenCaptureMailer
Takes a screenshot at regular intervals and is sent via email


# How to Install

Download or clone the repository with the following command : 
``` bash
git clone https://github.com/profumato4/ScreenCaptureMailer.git
```


# How to Use

- Open the repository with an IDE like Intellij IDEA, Eclipse etc.. 

- Edit the following lines of code in the `Main.java` file:

    ```java
    String fromEmail = "your-email@gmail.com";            // your email
    String password = "your-email-password";              // password app-specific password
    String toEmail = "recipient-email@gmail.com";         // recipient email
    String screenshotPath = "screenshot.png";
    int screenshotIntervalMillis = 300000;                // interval between screenshots (5 mins)
    ```
- It is advisable not to use the email password but to use an app password to log in, follow the instructions on the [link](https://support.google.com/accounts/answer/185833?hl=en) 

- After you have done all these things you can simply launch the file and based on the interval entered and other configurations you will receive screenshots regularly

# License 
This software is released under the MIT license. See the file [LICENSE](https://github.com/profumato4/ScreenCaptureMailer/blob/main/LICENSE) for further information.

# Questions or Problems?

If you have questions or encounter issues, please use [issue tracker](https://github.com/profumato4/ScreenCaptureMailer/issues) in the repository to report them.


