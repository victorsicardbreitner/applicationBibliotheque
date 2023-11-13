/*Administrateurs*/
INSERT INTO Personne ( role , nom, prenom, email, numtel, numallee, typevoie, ville, codepostal, pays, username, password)  
		VALUES ('Administrateur','Soulef', 'Saoud', 'soulefsaoud@biblio.fr', '06XXXXXXXX', '5', 'rue de la Biologie', 'Paris', '75012', 'France', 'SoulefS', 'Eucaryote');		
INSERT INTO Personne ( role , nom, prenom, email, numtel, numallee, typevoie, ville, codepostal, pays, username, password)  
		VALUES ('Administrateur','Victor', 'Sicard', 'victor.sicard@biblio.fr', '06XXXXXXXX', '8', 'rue des Mathématiques', 'Paris', '75012', 'France', 'VictorS', 'Cauchy-Schwartz');
INSERT INTO Personne ( role , nom, prenom, email, numtel, numallee, typevoie, ville, codepostal, pays, username, password)  
		VALUES ('Administrateur','Roland', 'Panzou', 'roland.panzou@biblio.fr', '06XXXXXXXX', '3', 'rue de la Chimie', 'Paris', '75012', 'France', 'RolandP', 'Helium');



/*Domaine*/
INSERT INTO Domaine ( nom, description)  VALUES ('Fantasy','La fantasy, terme issu de l’anglais fantasy est un genre artistique et littéraire qui représente des phénomènes surnaturels imaginaires.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Littérature contemporaine', 'Œuvres littéraires écrites à l’époque moderne et traitant de thèmes actuels.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Romans policiers', 'Romans centrés sur une enquête ou une résolution de crime.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Science-fiction et fantasy', 'Genres qui explorent des mondes imaginaires, futuristes ou fantastiques.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Biographies et mémoires', 'Récits de la vie de personnes réelles.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Histoire', 'Étude des événements, cultures et personnes du passé.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Géographie et voyages', 'Étude des lieux et cultures du monde.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Sciences sociales', 'Étude des comportements humains et des sociétés.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Philosophie', 'Exploration des idées fondamentales sur la réalité, la moralité et la connaissance.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Religion et spiritualité', 'Étude des croyances et pratiques spirituelles.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Sciences naturelles', 'Étude des phénomènes naturels incluant la biologie, la physique, la chimie et l’astronomie.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Mathématiques', 'Étude des nombres, quantités, formes et motifs.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Technologie et ingénierie', 'Étude et application des inventions et innovations technologiques.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Médecine et santé', 'Connaissance et pratique de la santé et du bien-être.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Arts', 'Formes diverses d’expressions artistiques, telles que la peinture, la musique, et le théâtre.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Littérature pour enfants', 'Ouvrages écrits spécifiquement pour un jeune public.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Poésie', 'Forme d’expression littéraire privilégiant le rythme et la métaphore.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Bandes dessinées et mangas', 'Histoires racontées à travers des illustrations séquentielles.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Cuisine et gastronomie', 'Arts de préparer et de déguster des repas.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Sports et loisirs', 'Activités physiques et récréatives pour le plaisir ou la compétition.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Informatique et programmation', 'Étude et création de systèmes informatiques et de logiciels.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Économie et affaires', 'Étude de la production, distribution et consommation des biens et services.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Linguistique et langues étrangères', 'Étude des langues et de leur structure.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Guides pratiques et manuels', 'Ouvrages fournissant des instructions ou des informations sur un sujet spécifique.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Science et environnement', 'Étude de la nature et de l’impact humain sur notre planète.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Mode et design', 'Arts de créer des vêtements et des objets esthétiquement plaisants.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Ouvrages de référence', 'Sources d’information comme les dictionnaires et encyclopédies.');
INSERT INTO Domaine ( nom, description) VALUES ( 'Éducation et pédagogie', 'Étude de l’enseignement et des méthodes éducatives.');

/*Incidents*/
INSERT INTO Incident (motif) VALUES ('l’etiquette se decolle');

/*Livres*/
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'JKR', 1, 'BON_ETAT', 'Harry Potter et la Pierre Philosophale', 1);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Haruki Murakami', 1, 'BON_ETAT', 'Kafka sur le rivage', 2);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Agatha Christie', 1, 'BON_ETAT', 'Le crime de l’Orient-Express', 3);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Isaac Asimov', 1, 'BON_ETAT', 'Les Robots', 4);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Anne Frank', 1, 'BON_ETAT', 'Le Journal d’Anne Frank', 5);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Yuval Noah Harari', 1, 'BON_ETAT', 'Sapiens: Une brève histoire de l’humanité', 6);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Paul Theroux', 1, 'BON_ETAT', 'Le Grand Bazar ferroviaire', 7);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Malcolm Gladwell', 1, 'BON_ETAT', 'Le point de basculement', 8);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Platon', 1, 'BON_ETAT', 'La République', 9);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Dalai Lama', 1, 'BON_ETAT', 'La voie vers l’éveil', 10);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Stephen Hawking', 0, 'BON_ETAT', 'Une brève histoire du temps', 11);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Marcus du Sautoy', 1, 'BON_ETAT', 'L’Énigme des nombres premiers', 12);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Walter Isaacson', 1, 'BON_ETAT', 'Steve Jobs', 13);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Atul Gawande', 1, 'BON_ETAT', 'La Checklist: Comment faire les choses bien', 14);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Vincent Van Gogh', 1, 'BON_ETAT', 'Lettres à Théo', 15);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Roald Dahl', 1, 'BON_ETAT', 'Charlie et la Chocolaterie', 16);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Robert Frost', 1, 'BON_ETAT', 'La route non prise', 17);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Akira Toriyama', 1, 'BON_ETAT', 'Dragon Ball', 18);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Julia Child', 1, 'BON_ETAT', 'Maîtriser l’art de la cuisine française', 19);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Phil Jackson', 1, 'BON_ETAT', 'Onze anneaux', 20);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Mark Lutz', 1, 'BON_ETAT', 'Apprendre Python', 21);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Adam Smith', 1, 'BON_ETAT', 'La richesse des nations', 22);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Noam Chomsky', 1, 'BON_ETAT', 'Théories syntaxiques', 23);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Marie Kondo', 1, 'BON_ETAT', 'La magie du rangement', 24);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'David Attenborough', 1, 'BON_ETAT', 'La vie sur Terre', 25);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Tim Gunn', 1, 'BON_ETAT', 'Le guide du style', 26);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Encyclopedia Britannica', 1, 'BON_ETAT', 'Encyclopédie Britannica', 27);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Maria Montessori', 1, 'BON_ETAT', 'L’enfant', 28);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'T.A. Barron', 1, 'BON_ETAT', 'Les aventures de Merlin', 1);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Christopher Paolini', 1, 'BON_ETAT', 'Eragon', 1);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Khaled Hosseini', 1, 'BON_ETAT', 'Les cerfs-volants de Kaboul', 2);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Jodi Picoult', 1, 'BON_ETAT', 'La couleur de la vérité', 2);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Raymond Chandler', 1, 'BON_ETAT', 'Le grand sommeil', 3);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Dashiell Hammett', 1, 'BON_ETAT', 'Le faucon maltais', 3);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Frank Herbert', 1, 'BON_ETAT', 'Dune', 4);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Orson Scott Card', 1, 'BON_ETAT', 'La stratégie Ender', 4);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Walter Isaacson', 1, 'BON_ETAT', 'Albert Einstein', 5);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Laura Hillenbrand', 1, 'BON_ETAT', 'Invincible', 5);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Jared Diamond', 1, 'BON_ETAT', 'Effondrement', 6);
INSERT INTO Livre ( auteur, dispo, etat, titre, domaine) VALUES ( 'Howard Zinn', 1, 'BON_ETAT', 'Une histoire populaire des États-Unis', 6);

/*Lecteurs*/
INSERT INTO Personne ( role , nom, prenom)  VALUES ('Lecteur','Dirac','Paul');
INSERT INTO Personne ( role , nom, prenom)  VALUES ('Lecteur','Curie','Marie');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Smith', 'John');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Martinez', 'Sofia');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Nakamura', 'Hiroshi');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Kaur', 'Simran');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Al-Mansoor', 'Ahmed');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'El-Hassan', 'Layla');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Okafor', 'Chijioke');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Adesanya', 'Amina');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Leroux', 'Olivier');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Dupont', 'Isabelle');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Wang', 'Li');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Chen', 'Ying');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'García', 'Carlos');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Gonzalez', 'Maria');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Patel', 'Raj');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Desai', 'Priya');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Ibrahim', 'Mohamed');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Musa', 'Fatimah');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Andersen', 'Erik');
INSERT INTO Personne ( role, nom, prenom) VALUES ( 'Lecteur', 'Johansen', 'Anna');

/*Emprunts*/
INSERT INTO Emprunt (id_lecteur , id_livre , date_debut , date_fin , etat, incident) VALUES (11, 11, TO_DATE('2023-09-04 03:05:30', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2023-09-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),  'EFFECTIF', 1);


		
		


				



