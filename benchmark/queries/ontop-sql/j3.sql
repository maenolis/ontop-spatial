SELECT 
   1 AS "s1QuestType", NULL AS "s1Lang", ('http://geo.linkedopendata.gr/gag/ontology/geometry/' || REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(CAST(QVIEW1."gid" AS VARCHAR(10485760)),' ', '%20'),'!', '%21'),'@', '%40'),'#', '%23'),'$', '%24'),'&', '%26'),'*', '%42'), '(', '%28'), ')', '%29'), '[', '%5B'), ']', '%5D'), ',', '%2C'), ';', '%3B'), ':', '%3A'), '?', '%3F'), '=', '%3D'), '+', '%2B'), '''', '%22'), '/', '%2F') || '/') AS "s1", 
   1 AS "s2QuestType", NULL AS "s2Lang", ('http://linkedgeodata.org/ontology//buildings/' || REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(CAST(QVIEW2."gid" AS VARCHAR(10485760)),' ', '%20'),'!', '%21'),'@', '%40'),'#', '%23'),'$', '%24'),'&', '%26'),'*', '%42'), '(', '%28'), ')', '%29'), '[', '%5B'), ']', '%5D'), ',', '%2C'), ';', '%3B'), ':', '%3A'), '?', '%3F'), '=', '%3D'), '+', '%2B'), '''', '%22'), '/', '%2F') || '/') AS "s2"
 FROM 
gag QVIEW1,
buildings QVIEW2
WHERE 
QVIEW1."gid" IS NOT NULL AND
QVIEW1."geometry" IS NOT NULL AND
QVIEW2."gid" IS NOT NULL AND
QVIEW2."geom" IS NOT NULL AND
(ST_Intersects(QVIEW1."geometry",QVIEW2."geom"))

