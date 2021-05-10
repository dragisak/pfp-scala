package pfp.core

import squants.Money

final case class CartTotal(items: List[CartItem], total: Money)
