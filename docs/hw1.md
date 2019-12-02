save
Храните все резюме в начале storage 
    (без дырок в виде null)

get


delete


size


clear


getAll



Схема хранения резюме в массиве storage 
(в элементах от 0 до size-1 отсутствуют null):
r1, r2, r3,..., rn, null, null,..., null
<----- size ----->
<------- storage.length (10000) ------->