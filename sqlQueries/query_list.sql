-- All the entrees
SELECT * FROM MenuItems WHERE foodType = 'entree';

-- All menu items from order 0
SELECT menu.*
FROM MenuItems menu 
JOIN OrderItems ords 
ON menu.id = ords.menuItem1 OR menu.id = ords.menuItem2 OR menu.id = ords.menuItem3 OR menu.id = ords.menuItem4 OR menu.id = ords.menuItem5
WHERE ords.id = 0;