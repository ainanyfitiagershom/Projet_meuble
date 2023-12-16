create database testS5S3;
\c testS5S3

create table etudiant(   
    idetudiant SERIAL PRIMARY KEY,
    numero int,
    nom varchar(50),
    prenom varchar(60),
    dtn date,
    mdp varchar(50)
    );

    insert into etudiant(numero, nom, prenom, dtn) values(1776, 'lagyb', 'Andy', '2005-05-27')

     insert into etudiant(numero, nom, prenom, dtn) values(1776, 'lagyb', 'Andy', '2005-05-27');
      insert into etudiant(numero, nom, prenom, dtn) values(3333, 'A', 'Aaa', '2005-05-27');
       insert into etudiant(numero, nom, prenom, dtn) values(4444, 'B', 'Bbb', '2006-06-27');
        insert into etudiant(numero, nom, prenom, dtn) values(5555, 'C', 'Ccc', '2007-07-27');
         insert into etudiant(numero, nom, prenom, dtn) values(6666, 'D', 'Ddd', '2008-08-27');
          insert into etudiant(numero, nom, prenom, dtn) values(7777, 'E', 'Eee', '2009-09-27');
           insert into etudiant(numero, nom, prenom, dtn) values(8888, 'F', 'Fff', '2010-10-27');


           create table produit(
            id SERIAL PRIMARY KEY,
            nom varchar(50)
           );

           insert into produit(nom) values('seza');

           create table style(
            id SERIAL PRIMARY KEY,
            nom varchar(50)
           );

           create table matiere_premier(
            id SERIAL PRIMARY key,
            nom varchar(50)
           );

           create table matiere_style(
            id SERIAL PRIMARY KEY,
            idmp int references matiere_premier(id),
            idstyle int references style(id)
           );

           create or replace view matiere_par_style as
            select s.id as idstyle, 
                    s.nom as nomstyle,
                    m.id as idmp,
                    m.nom as nommp
                from matiere_style as ms 
                    join matiere_premier as m on ms.id = m.id
                    join style as s on ms.id = s.id;