# guido Engine
Implementation for an html crawler.
To run the application, just download the GMARCHINI-crawler.jar and then run the following command on console:
java -jar GMARCHINI-crawler.jar "/path/to/original.html" "/path/to/modified.html"

Outputs from the original html with:
scenario 1:
"html > body > div > div > div > div > div > div"

scenario 2:
"html > body > div > div > div > div > div > div > div"

scenario 3:
"html > body > div > div > div > div > div > div"

scenario 4:
"html > body > div > div > div > div > div > div"


TODO:
return the position of the tag, if multiple tags are present in the parent