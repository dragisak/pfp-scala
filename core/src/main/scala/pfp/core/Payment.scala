package pfp.core

import squants.Money

final case class Payment(id: UserId, total: Money, card: Card)
