package pfp.core

import squants.Money

final case class Item(
    id: ItemId,
    name: ItemName,
    description: ItemDescription,
    price: Money,
    brand: Brand,
    category: Category
)
