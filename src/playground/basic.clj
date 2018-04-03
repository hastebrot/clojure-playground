(ns playground.basic
  (:use [clojure.test :refer :all]))

(cons 1 '(2 3 4))
(conj '(1 2 3) 4)

(partition 2 '(1 2 3 4 5 6 7))
(partition 3 2 '(1 2 3 4 5 6 7))

(nthrest
  '(1 2 3 4 5 6)
  2)

(deftest basic-test
  (testing "arithmetic"
    (is (= (+ 2 2) 4))))

