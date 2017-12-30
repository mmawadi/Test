To install this web in local machine you need eclipse and jdk 1.8, No need to any extra or external jar files because this is a maven project so its will download the needed jars by itself.

I used GlassFish4 in development but it supposed to work fine with tomcat.

Issues and Assumptions :

1- while parsing the given file I couldn't find the Total Rate so I used hotelReviewTotal for Total Rate option.

2- I don't check the structure of the Json file before parsing it. so if the json file structure does not match the one I expect a null exception will be thrown. the thrown exception will be ignored and returning the parsed data or empty list.

3- In the GUI I don't make sure that the checkout date is after the checkin date.
