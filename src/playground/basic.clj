(ns playground.basic
  (:use [clojure.test :refer :all]))

(println (cons 1 '(2 3 4)))
(println (conj '(1 2 3) 4))

(println (partition 2 '(1 2 3 4 5 6 7)))
(println (partition 3 2 '(1 2 3 4 5 6 7)))

(println (nthrest
           '(1 2 3 4 5 6)
           2))

(deftest basic-test
  (testing "arithmetic"
    (is (= (+ 2 2) 4))))

