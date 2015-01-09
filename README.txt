Rent a car application in Java/Swing/MySQL technologies. Insertion, editing, deletion via forms. Xls generator of reports. 

/////////////////////////////

- developed on JDK 1.7
- developed with NetBeans as Swing application
- 4 main classes in project: 
RentACar.java - main project file, which starts app and contains functions that are generating view.
DatabaseManager.java - db connectio file (creating of tables also), contains code for reading, insertion, editing and deletion of data
GetTabularData.java - generates table view of result list
Helper.java - code with some help methods (adding numbers inside table view for example, setting table cell with based on header column, wrapping of text, text limiter, xls generator, etc).

- below JDK, running MySQL server is required
- app should install db dump, alternative is to use dump file
- dump is in RentACar/rent_a_car.sql file

Additional: 
- database design was because strict orders

Scripts for db dump:
- DatabaseManager.java, method: getConnection (db creation) and also: createTables (tables creation)

Populating data: 
- DatabaseManager.java, method insertQuery

Reports file:
- DatabaseManager.java, method getReport


Copyright Dario Benšić

/////////////////////////////

Rješenje zadatka za "Rent a car" aplikaciju (Java/Swing/MySQL): 

Par napomena u vezi rješenja zadatka:
- zadatak je testiran na JDK 1.7 i rađen kao Swing aplikacija
- zadatak je rađen u NetBeans programu te vam šaljem zip projekta koji se nalazi na putanji http://www.web-place.info/scripts/RentACar/RentACar.zip
- postoje 4 klase u projektu: 
RentACar.java - glavni file projekta, koji pokreće aplikaciju te sadrži funkcije koje generiraju izgled aplikacije
DatabaseManager.java - file koji sadrži konekciju na bazu, kreiranje tablica, te koji sadrže funkcionalnosti za čitanje, unos, promjenu i brisanje podataka.
GetTabularData.java - file koji generira tablični prikaz izlista rezultata
Helper.java - file koji sadrži neke od funkcionalnosti za upravljanje pojedinim dijelovima aplikacije (dodavanje gumbiju unutar tabličnog prikaza, setiranje dimenzija tabličnog prikaza prema naslovu header kolone, naštimavanje teksta ćelija da stanu u prikaz, ograničenje broja znakova za tekstualna polja, eksport u Excell, itd).

- da biste pokrenuli aplikaciju, nužno je osim JDK verzije imati raspakirati navedenu .zip datoteku te omogućiti pristup MySQL serveru.
- za RDBMS sistem je korišten MySQL tako da ćete prilikom pokretanja programa morati imati uključen MySQL server, a aplikacija će vam sama instalirati bazu i tablice (u attachmentu ću vam svejedno poslati dump baze)
- editiranje i brisanje je rađeno samo na automobilima, te stavke nisam radio za prihode i troškove
- za generiranje izvještaja dodatno je omogućen i export u Excell format za pojedinu godinu.
- Dump baze se nalazi na putanji: http://www.web-place.info/scripts/RentACar/rent_a_car.sql

Dodatno: 
- baza je mogla biti i bolje relacijski povezana no držalo se striktnih uputa o kolonama tablica

Skriptu za kreiranje baze:
- nalazi se u file-u DatabaseManager.java u metodi naziva getConnection (kreira bazu) kao i metodi naziva createTables (kreira tablice)

Skripta punjenje podataka: 
- nalazi se u file-u DatabaseManager.java u metodi naziva insertQuery

Skripta za dobivanje rezultata za izvješće:
- nalazi se u file-u  DatabaseManager.java u metodi naziva getReport

Projekt sa pripadnim izvornim kodom:
- u attachmentu


Copyright Dario Benšić