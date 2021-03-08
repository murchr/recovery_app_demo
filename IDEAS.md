# Future modifications to implentations

## Features to add

These features are as follows:
- **Pain heat map** - record pain on an image of the human body to show the intensity, 
location, and type of pain they are currently experiencing.
- longitudinal representation of pain heat map to show progression of perceived injury
- **non localized pain log** - record of injuries or illnesses that do not conform to
localized pain, and would not be appropriate for a heat map approach
- **Sleep log** - record of sleep duration and perceived quality of sleep
- **Mental Health Log** - bullet journal style record of mood and relevant mental health 
factors
- **Visualization Tools** - visualizations of various statistics based on log data to help 
show progression in each of the provided metrics
- **Export Data** - potentially allowing for metrics to be exported to allow for health 
professionals to review relevant data to patient treatment

## Modifications to implementation

- For LogEntries have set functions throw OutOfRange exceptions
    - add handling for exceptions to underlying functions
    - test exception handling
- TBD