* Three file loaders implemented the same interface.
* Different implements to read XML, JSON and CSV.
* Customized add() to exclude records with packets-serviced equal to zero.
* A map to record records nums identified by service-guid.
* Record object should implement Comparable interface, so can sort the list by Collections.sort().
* Compare record object by request-time field.