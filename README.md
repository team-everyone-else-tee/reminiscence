# Reminiscence

[Reminiscence](https://reminiscences.herokuapp.com/)

A journalling web application that helps our users gain awareness of how the tone  they choose to use has an influence in shaping their experiences. 
Users can sign up for a unique account, and then post, edit, and delete journal entries. Journal entries have a 5000 character limit. On the service, one can only see and manipulate the entries associated with their own account. 

When a post is made, the body of it is sent to IBM watson, watson will analyze the content of the entry for the tones that make up the expression. Watson will then return as many of the below tones that apply, along with its confidence that that specific tone was present in the body. Watsons possible tone returns are: Anger, Fear, Joy, Sadness, Analytical, Confident, and Tentative.

Entries are decorated with the tones that were found within, and the tones associated with a user can be graphed to visual the breakdown of their frequently used tones.


#### Resources
[Date Time](http://tutorials.jenkov.com/java-internationalization/simpledateformat.html)
We had been blocked by getting our timestamps in a useful format

[Watson Auth](https://github.com/watson-developer-cloud/java-sdk#installation)
This resource helped us achieve our first contact with Watson

[Java Watson](https://cloud.ibm.com/apidocs/tone-analyzer?code=java)
The watson java docs are amazing, we references them frequently

[Highchart JS Tutorial](https://github.com/Java-Techie-jt/Spring-HighChart-Graph)
We spring boarded off of this tutorial to get started with highchartjs, ultimately it was a very painfree process for data visualiation that didnt require anything more than filling a model and dropping values with thymeleaf

[Meyer Web Reset](http://meyerweb.com/eric/tools/css/reset/)  v2.0 | 20110126
A css reset

[Modal Logic](https://ctrlq.org/code/20083-modal-popup-css)
A tutorial to help us understand how css modals work, we used to to create and hide a warning on our Put route. 

#### Potential Resources
[Hamburger Menu](https://codepen.io/erikterwan/pen/EVzeRP)
