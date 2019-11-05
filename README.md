# guido Engine
Implementation for an html crawler.
To run the application, just download the GMARCHINI-crawler.jar and then run the following command on console:
java -jar GMARCHINI-crawler.jar "/path/to/original.html" "/path/to/modified.html"

Outputs from the original html with:
scenario 1:
html > body > div > div > div[2] > div[0] > div > div[1] > a[1]

scenario 2:
html > body > div > div > div[2] > div[0] > div > div[1] > div > a

scenario 3:
html > body > div > div > div[2] > div[0] > div > div[2] > a

scenario 4:
html > body > div > div > div[2] > div[0] > div > div[2] > a