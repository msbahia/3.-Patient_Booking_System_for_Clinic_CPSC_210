# "My Personal Project"
# An appointment booking system of a clinic

## Name of Clinic: LifeClinic

### What the project will look like

- *The clinic will a Doctor to book with.*
- *Program will allow patients to do bookings.*
- *Program will allow patients to change booking slot.*
- *Program will allow user to check bookings list.*



## Instructions for grader:
### To register a patient
- Type name of patient (string) in field corresponding to "Patient Name" label.
- Type booking time (integer) in field corresponding to "booking time" label.
- Then, click button labeled as "Add Patient".
- Patient will be registered only if the given time slot is available, otherwise try changing the time.
- The visual component of these operations is displayed in the "Remarks" field, e.g. (if patient booked or not).
- Finally, the booked patient and time information is displayed in the "Booking List" filed.

### To change booking of a patient
- Type name of patient (string) in field corresponding to "Patient Name" label.
- Type booking time (integer) in field corresponding to "booking time" label.
- Then, click button labeled as "Change Booking".
- Booking will be changed only if the new requested time slot is available, otherwise try changing the time slot. 
- The visual component of these operations is displayed in the "Remarks" field, e.g. (if patient booking changed or not).
- Finally, the changed booking of patient is displayed in the "Booking List" filed.

### To cancel booking of a patient
- Type name of patient (string) in field corresponding to "Patient Name" label.
- Type booking time (integer) in field corresponding to "booking time" label.
- Then, click button labeled as "Cancel Booking".
- Booking will be cancelled only if the booking exists, otherwise, no such booking message is displayed.
- The visual component of these operations is displayed in the "Remarks" field, e.g. (if patient cancelled or not).
- Finally, the cancelled booking can be seen removed from the "Booking List" filed.

### To save and load all bookings
- After adding patients, press button labeled as "Save Booking" to save all bookings, 
- Press, button labelled as "Load Booking" to load all bookings.
- The visual component of these operations is displayed in "Remarks" field, e.g. (if booking loaded successfully or not).

### To display only booked appointments
- After adding patients, press button labeled as "Show Booking" to display only booked slots.
- The visual component of these operations is displayed in "Remarks" field.

### To Exit the program
- Press button labelled as "Exit".


### Phase 4: Task 2
- Wed Nov 23 17:16:32 PST 2022
- Malkeet is booked at 9 hr successfully!
- Wed Nov 23 17:16:39 PST 2022
- Jasmeen is booked at 10 hr successfully!
- Wed Nov 23 17:16:49 PST 2022
- Malkeet's booking is successfully changed from 9 to 21 hr
- Wed Nov 23 17:16:51 PST 2022
- Malkeet's booking at 21 is cancelled successfully!.
- Wed Nov 23 17:16:54 PST 2022
- Malkeet is booked at 15 hr successfully!
- Wed Nov 23 17:17:03 PST 2022
- Jasmeen's booking at 10 is cancelled successfully!.
- Wed Nov 23 17:17:06 PST 2022
- Jasmeen is booked at 12 hr successfully!
- Wed Nov 23 17:17:11 PST 2022
- Jasmeen's booking is successfully changed from 12 to 14 hr
- Wed Nov 23 17:17:16 PST 2022
- The booking list has been saved successfully!
- GUI closed successfully!

### Phase 4: Task 3

- In the given form, the design of this program is very simple and straightforward.
- The Doctor class has "name" of doctor and "booking behaviour", like make, change and cancel booking of a patient etc.
- If we need to refactor this program, then probably we need to add more functionality that will give us more
leverage to use refactoring principles.
- Let say we add more information about the Doctor, like contact details, speciality etc. then, this class will have 
multiple responsibilities: "booking behaviour" and "Doctor information (not only the name of doctor as of now)". 
- In such scenario, we will need to separate the two different behaviours out of the Doctor class.
- Then, we can make a separate class (or abstract class) that will handle booking behaviour, and leave Doctor class to handle the
Doctor details.
- Consequently, we can use booking behaviour as field in Doctor class, and  use the methods of booking behaviour
in Doctor class.
- In case, we add more Doctors in the program, then we need an interface that will handle the methods of Doctor class,
and different Doctor classes will implement them (like, name of doctor, availability, contact details are few examples).