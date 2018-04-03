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
    [(== n 'zero)]
    [(fresh [n-1]
            (== n `(~'succ ~n-1))
            (peano° n-1))]))

(run* [q] (peano° 'zero))                                   ;;=> (_0)
(run* [q] (peano° '(succ zero)))                            ;;=> (_0)
(run* [q] (peano° 1))                                       ;;=> ()

(run 10 [q] (peano° q))

;; peano number plus relation.
;; z + n = n
;; n1 + n2 = n3 <=> (s n1) + n2 = (s n3)
(defn plus [n1 n2]
  (match [n1]
         ['zero] n2
         [(['succ n1-1] :seq)] `(~'succ ~(plus n1-1 n2))))

(plus 'zero '(succ zero))                                   ;;=> (succ zero)
(plus '(succ zero) '(succ zero))                            ;;=> (succ (succ zeo))
(plus '(succ (succ zero)) '(succ zero))                     ;;=> (succ (succ (succ zero)))

;; peano number plus relation in core.logic.
(defn plus° [n1 n2 n3]
  (conde
    [(== n1 'zero) (== n2 n3)]
    [(fresh [n1-1 n3-1]
            (== n1 `(~'succ ~n1-1))
            (== n3 `(~'succ ~n3-1))
            (plus° n1-1 n2 n3-1))]))

(run* [q] (plus° 'zero '(succ zero) q))                     ;;=> ((succ zero))))
(run* [q] (plus° q '(succ zero) '(succ (succ zero))))       ;;=> ((succ zero))))

(run 10 [q] (fresh [a b c]
                   (== q [a b c])
                   (plus° a b c)))

