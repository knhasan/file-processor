# A Sample File Processor
***

## Overview

This project creates an application which takes a text file as an input and processes according to the following requirement:

Read a file (name passed in as 1st argument to program). Each line of the file has a category, a space, and a sub-category (sub-category can have whitespace within it). Process as follows:

* Only process the pair (category, sub-category) once
 * If a pair appears twice, ignore the second one
* Keep track of the order of the first occurrence of each pair
* Keep track of the count for each category
* Legal category values are: PERSON PLACE ANIMAL COMPUTER OTHER
  * Illegal category values should be ignored
* Output list of entries should be in the same order

Input and output example:

Input file:
<pre>
PERSON Bob Jones
PLACE Washington
PERSON Mary
COMPUTER Mac
PERSON Bob Jones
OTHER Tree
ANIMAL Dog
PLACE Texas
FOOD Steak
ANIMAL Cat
</pre>

Output:
<pre>
CATEGORY  COUNT
PERSON  2
PLACE   2
ANIMAL  2
COMPUTER 1
OTHER 1
 
PERSON Bob Jones
PLACE Washington
PERSON Mary
COMPUTER Mac
OTHER Tree
ANIMAL Dog
PLACE Texas
ANIMAL Cat
</pre>

## Requirements

* JDK 1.7+
* Maven 2

## Build

Run <pre>mvn install</mvn>


