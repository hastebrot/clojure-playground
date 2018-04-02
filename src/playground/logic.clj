(ns playground.logic
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is] :as l])
  (:use [clojure.core.match :only [match]])
  (:use [clojure.test]))

(defn peano° [n]
  (conde
    [(== n 'z)]
    [(fresh [n-1]
            (== n `(~'s ~n-1))
            (peano° n-1)
            )]))

(run* [q] (== q true))

(run* [q] (peano° 'z))

(run* [q] (peano° '(s z)))

(run* [q] (peano° 1))

(run 10 [q] (peano° q))

