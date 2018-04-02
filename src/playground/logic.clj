(ns playground.logic
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is] :as l])
  (:use [clojure.core.match :only [match]])
  (:use [clojure.test]))

;; peano numbers.
;; n := z | (s n)
(defn peano° [n]
  (conde
    [(== n 'z)]
    [(fresh [n-1]
            (== n `(~'s ~n-1))
            (peano° n-1)
            )]))

(run* [q] (== q true))                                      ;;=> (true)

(run* [q] (peano° 'z))                                      ;;=> (_0)

(run* [q] (peano° '(s z)))                                  ;;=> (_0)

(run* [q] (peano° 1))                                       ;;=> ()

(run 10 [q] (peano° q))

