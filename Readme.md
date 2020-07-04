# Loan Approval System
This is a Loan Approval system for a financial institute. Our financial institute receives many loan applications and reject them only if the applicant
doesn't qualify, otherwise the applications will be added to a list (called active list)
and will be approved once the budget is available.

The loan approval system allows the following commands:
##- Load the applications:
Reads the information of the applications from a file and store
them as active applications if they qualify (if an application does not meet the
requirements it will be added to the rejected application list)

##- Set the budget:
Update the current budget (the input for this command is some amount
of budget that will be added to the current budget of the institute. At the beginning the
current budget should be initialized as 0).
##- Make a decision:
given the current budget, make a decision about active applications
(approve as many applications as possible: i.e remove them from the active application
list and add them to the approved list).
##- Print:
print the list of active applications (no decision made so far), approved
applications and rejected applications in three separate log files (approved.txt,
rejected.txt, active.txt).
##- Update an application:
Applicants can update their application later by providing more
documents. This may affect a currently approved application or the rejected applications.
