# pharmacy_system

#### Na projektu radili :
####                    student 1 Maja Tomic RA 155/2017 
####                    student 3 Nemanja Jevtic RA 131/2017
#### Student 4 odustao od izrade projekta.


### Linkovi :
	      1. Travis: https://travis-ci.com/github/Maja0505/pharmacy_system
	      2. Sonar: https://sonarcloud.io/dashboard?id=Nemanja0505_pharmacy_system&branch=master
	      3. Heroku backend : https://pharmacies-system-isa.herokuapp.com/
	      4. Heroku frontend: https://pharmacies-system.herokuapp.com/
       
 ### Baza :
	      1. Za bazu je neophodno isntalirati postgreSQL
	      2. Naziv baze je isa-postgres
   
### Backend:
        Instalirati meven i podesiti konfiguraciju
        Ukoliko se radi u IntelliJ IDEA neophodno je podesiti enviroment variablu kao na linku
        Za variablu staviti sledece : -Dspring.profiles.active=local
        https://stackoverflow.com/questions/41244585/how-to-set-spring-active-profile-environment-variable-in-intellij/46345476

        Ukoliko se korisi Eclipse to se moze odraditi na sledeci dodati istu variablu : -Dspring.profiles.active=local
        https://stackoverflow.com/questions/38520638/how-to-set-spring-profile-from-system-variable        
        Pokrenuti aplikaciju
        
### Frontend:
        1.Intalirati Node.js i npm
        2.Iz command promt-a pozicionirati se na folder "front" i uneti sledece komande:
          -npm install
          -npm run local


Napomene :
	1. Zbog potreba testiranja od strane asistenata omoguceno je da farmaceut/dermatolog mogu da 
      pisu izvestaj za bilo koji termin koji je rezervisan u buducnosti.Provera uslova da farmaceut/dermatolog
      mogu da pisu izvestaj samo u vremenskom intervalu od pocetka trajanja termina pa do njegovog zavrsetka 
      izostavljena je samo radi mogucnosti lakse testiranja aplikacije.
  2. Email na koje salju notifikacije je : isa2020.team36 , a sifra : isa2020isa
  3. Za logovanje sife su sve 12345
  4. Najvise podataka ima za korisnike :
  5. patient1@gmail.com
  6. dermatologist1@gmail.com
  7. pharmacist1@gmail.com
	
