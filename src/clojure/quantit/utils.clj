(ns quantit.utils
  (:require [tick.alpha.api :as t])
  (:import (java.time ZonedDateTime)
           (java.util GregorianCalendar)))

(defn end? [x] (= x 'end))

(defn flat-seq->map [x]
  (->> x (partition 2) (map vec) (into {})))

(defn inspect [body]
  (prn body)
  body)

(defn nyse-market-open-date-time
  ([]
   (-> (t/new-date)
       (t/at "09:30")
       (t/in "GMT-6")))
  ([local-date]
   (-> local-date
       (t/at "09:30")
       (t/in "GMT-6"))))

(defn nyse-market-close-date-time
  ([]
   (-> (t/new-date)
       (t/at "16:00")
       (t/in "GMT-6")))
  ([local-date]
   (-> local-date
       (t/at "16:00")
       (t/in "GMT-6"))))

(defn zoned-date-time->calendar [^ZonedDateTime date-time]
  (GregorianCalendar/from date-time))
