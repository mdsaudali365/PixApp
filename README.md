# PixApp

This is a hobby project to download images from Instagram using Selenium web driver and java 8. The application utilizes Spring integration to start up and download images. As of now, it works on Windows with Firefox v47. Am happy if someone has any points to improve the capability:)

Project operate manual:

Prerequisites:
1) Java 8 JRE
2) Firefox v47
3) Facebook account linked with instagram account – means you should be able to login to instagram with your facebook credentials
4) geckodriver.exe should be in the same folder where the jar exists
5) Download folder should be created prior to running the application

How it works:
Solution is built using Selenium integrated with Spring Boot. The boot configuration takes the following arguments from command line:
--client_name="Sonam Kapoor" –fb_id=dummyid@fb.com --fb_pass=userpass --loops=1 --download_dir="C:\Users\mohammad saud ali\Desktop\PixGetterRunner\downloads"

Client Name – Set the name of the person you wish to download images of. This program can be used to download images of any public account.
Fb id – Email address used to login to instagram account that is linked to facebook
Fb pass – Password of the facebook account
Loops – Since instagram loads the image gallery dynamically, we have to set how many pages are to be loaded. For example: if a user has 45 images, it will take atleast 4 loops to scan his gallery and download the images. 
Download dir – Mention the location where you want the images to be downloaded. Kindly do NOT mention “\” at the last position.

How to run:
I have added a convinience batch file which has the following contents:
java -jar pixapp-0.0.1-SNAPSHOT.jar --client_name="Sonam Kapoor" –fb_id=dummyid@fb.com --fb_pass=userpass --loops=1 --download_dir="C:\Users\mohammad saud ali\Desktop\PixGetterRunner\downloads"
pause

Kindly update the parameters based on your requirement and run the file to see the magic!

Enhancements to be done:
Following enhancements can be done to the application:
Make the application run as a background process to download images in real time – recent images
Can store the images in the database
Implement logging framework
	
