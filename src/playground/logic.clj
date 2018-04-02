(ns playground.logic
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is] :as l])
  (:use [clojure.core.match :only [match]])
  (:use [clojure.test]))

(run* [q] (== q true))                                      ;;=> (true)

;; peano numbers in core.logic.
;; n := z | (s n)
(defn peano° [n]
  (conde
    [(== n 'z)]
    [(fresh [n-1]
            (== n `(~'s ~n-1))
            (peano° n-1)
            )]))

(run* [q] (peano° 'z))                                      ;;=> (_0)
(run* [q] (peano° '(s z)))                                  ;;=> (_0)
(run* [q] (peano° 1))                                       ;;=> ()

(run 10 [q] (peano° q))

;; peano number plus relation.
;; z + n = n
;; n1 + n2 = n3 <=> (s n1) + n2 = (s n3)
(defn plus [n1 n2]
  (match [n1]
         ['z] n2
         [(['s n1-1] :seq)] `(~'s ~(plus n1-1 n2))
         ))

(plus 'z '(s z))                                            ;;=> (s z)
(plus '(s z) '(s z))                                        ;;=> (s (s z))
(plus '(s (s z)) '(s z))                                    ;;=> (s ((s (s z)))

;; peano number plus relation in core.logic.
(defn plus° [n1 n2 n3]
  (conde
    [(== n1 'z) (== n2 n3)]
    [(fresh [n1-1 n3-1]
            (== n1 `(~'s ~n1-1))
            (== n3 `(~'s ~n3-1))
            (plus° n1-1 n2 n3-1)
            )]
    ))

(run* [q] (plus° 'z '(s z) q))                              ;;=> ((s z))))
(run* [q] (plus° q '(s z) '(s (s z))))                      ;;=> ((s z))))

(run 10 [q] (fresh [a b c]
                   (== q [a b c])
                   (plus° a b c)
                   ))

