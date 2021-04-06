# Pain, Health, & Wellness

## A cronic pain and recovery monitor application

This application aims to deliver a logging system for two main demographics, those 
suffering **cronic pain** and those recording their **recovery process** of various
injuries. The main way this will be achieved is by implementing several systems for
users to monitor their pain and lifestyle habits. This is not a coaching app but simply
a method for users to catalog their ailments and determine if they are improving over
a period of time. This longitudinal representation will change based off the type of data
provided by users and will allow them to more easily record recurring symptoms and behaviours.  

These features are as follows:
- **Pain heat map** - record pain on an image of the human body to show the intensity, 
location, and type of pain they are currently experiencing.
- longitudinal representation of pain heat map to show progression of perceived injury
- **non localized pain log** - record of injuries or illnesses that do not conform to
localized pain, and would not be appropriate for a heat map approach
- **Exercise log** - record of type of exercise, relative intensity, and duration
- **Weight log** - record of individual weight
- **Sleep log** - record of sleep duration and perceived quality of sleep
- **Mental Health Log** - bullet journal style record of mood and relevant mental health 
factors
- **Visualization Tools** - visualizations of various statistics based on log data to help 
show progression in each of the provided metrics
- **Export Data** - potentially allowing for metrics to be exported to allow for health 
professionals to review relevant data to patient treatment

## Phase 1: User Stories

- As a user, I want to be able to add ExerciseEntries to my DailyLog's exercisesLog
- As a user, I want to be able to see how many minutes of exercise have been done on DailyLog
- As a user, I want to be able to view all ExerciseEntries in a DailyLog
- As a user, I want to be able to remove a specific ExerciseEntry by entryId from DailyLog

## Phase 2: User Stories

- As a user, I want to be able to be able to load an instance of DailyLogMap from a designated file
- As a user, I want to be able to load an instance of the last accessed instance of DailyLogMap
- As a user, I want to be able to add DailyLog entries from a file to runtime DailyLogMap
- As a user, I want to be able to save my DailyLogMap to a file.

## Phase 4: Task 2

- As a user, I want creation of new LogEntries to be robust against invalid input.
ExerciseEntry/WeightEntry constructors
- gui implementation of a type hierarchy with abstract class NewEntryFrame and subclasses
NewExerciseFrame/NewWeightFrame. Overwritten class: storeEntries(), initializeItems(), initializeItems(Log Entry)

## Phase 4: Task 3

- As is apparent in the diagram the project is designed such that additional features data
fields can be added to the app by adding it as a field to DailyLog/DailyLogPanel and generating
appropriate subclasses of logEntry, LogList, SummaryStat, EntryVis, LogListVis, and LogListPanel.
- The structure for the RecoveryApp for the most part is clean however if given more time the structure
of DailyLogMap and LogList should be refactored to encapsulate rather than extend the Java ConcurrentSkipListMap
and ArrayList classes respectively. Additionally, further refactoring should be executed to divide MemoryHandling
into classes to handle Addresses and RecoveryApp independently.
- The structure for the RecoveryAppUI would benefit from altering passing of the RecoveryApp data within the
RecoveryAppGui and down its class structure. Next, refactoring the LogListPanel, LogOptionPanel, and
LogListVis into a single class structure may be more coherent in reducing the split of closely related 
functionality.