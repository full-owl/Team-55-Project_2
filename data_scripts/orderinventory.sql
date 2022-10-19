create view orderingredients as
select orderid, i.id as inventoryid, i.ingredient, sum(m3.amountneeded*m2.proportion) as amountneeded, i.unit
from orderitems
join menuitems m on m.id = orderitems.menuitem1 or m.id = orderitems.menuitem2 or m.id = orderitems.menuitem3 or m.id = orderitems.side1 or m.id = orderitems.side2
join menuingredients m2 on m.id = m2.menuid
join mealsizes m3 on m3.mealtype = orderitems.mealtype and (m3.foodtype = m.foodtype)
join inventory i on i.id = m2.inventoryid
group by orderid, i.id;
