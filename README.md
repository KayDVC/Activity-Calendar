## Table of Contents
1. [Objective](#objective)
2. [Use](#use)
    * [Input Files](#input-files)
    * [Example Run](#example-run)


## Objective
The objective of this project was to develop a practical application that uses a [skip list](https://en.wikipedia.org/wiki/Skip_list) as the main data structure. This was completed in 2021 as an assignment.

The application features database-like storage and commands to add, retrieve, and view data.

- _Note_ : The [FakeRandHeight.java](src/util/FakeRandHeight.java) class was created by my professor, Dr. Philip Chan, and is properly credited within the file.

## Use

Requires Java. Tested using `openjdk 17.0.8`.

### Input Files

The app requires the use of an input text file. 

The following commands are valid.

```
AddActivity time activity
RemoveActivity time
GetActivity time
GetActivitiesBetweenTimes startTime endTime
GetActivitiesForOneDay date
GetActivitiesFromEarlierInTheDay currentTime
PrintSkipList
```

*All times values should be in mmddhhmm format except for the  "GetActivitiesForOneDay" command; use mmdd format instead.*

_See examples in `samples`_

### Example Run

```bash
cd src
javac ActivityCalendar.java
java ActivityCalendar ../samples/One.txt

Command received:
	AddActivity 03200930 Cycling

	✅ Command executed successfully
--------------------

Command received:
	AddActivity 03211100 Jogging

	✅ Command executed successfully
--------------------

Command received:
	AddActivity 03231045 Hiking

	✅ Command executed successfully
--------------------

Command received:
	AddActivity 03231945 Workout

	✅ Command executed successfully
--------------------

Command received:
	GetActivity 03211100

Response:
	Jogging

	✅ Command executed successfully
--------------------

Command received:
	GetActivitiesForOneDay 0323

Response:
	03231045 : Hiking 
	03231945 : Workout 

	✅ Command executed successfully
--------------------

Command received:
	GetActivitiesFromEarlierInTheDay 03231600

Response:
	03231045 : Hiking 

	✅ Command executed successfully
--------------------

Command received:
	PrintSkipList

Response:
	(L2)  03231945 : Workout
	(L1)  03211100 : Jogging,  03231945 : Workout
	(L0)  03200930 : Cycling,  03211100 : Jogging,  03231045 : Hiking,  03231945 : Workout

	✅ Command executed successfully
--------------------
```

Don't degrade yourself by using Eclipse🤢; [here's a tutorial](https://code.visualstudio.com/docs/java/java-tutorial) on how to setup VS Code for Java. Basic, but at least you'll be able to sleep at night with dignity and self-respect.

If used as "inspiration," please link back to this repo.

Thanks,

\- Kay
