Feature: DatTable Feature

  Scenario: Test Table Input
    Given below members:
      | weight | energy | protein |
      |    450 |  26500 |     215 |
      |    500 |  29500 |     245 |
      |    575 |  31500 |     255 |
      |    600 |  37000 |     305 |
    And below cosume info:
      | weight | energy | protein |
      |    450 |  26500 |     215 |

    When Execute Settlement
    Then Result should be like this
      | weight | energy | protein |
      |    450 |  26500 |     215 |
      |    450 |  26500 |     215 |

