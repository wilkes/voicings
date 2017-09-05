(ns voicings.events
  (:require [re-frame.core :as re-frame]
            [voicings.db :as db]
            [clojure.string :as str]))

(defn inversions [root-voicing]
  (loop [invs [] notes (cycle root-voicing)]
    (if (= (count invs) (count root-voicing))
      invs
      (recur (conj invs (take (count root-voicing)
                              notes))
             (drop 1 notes)))))

(defn drop-2 [[a b c d]]
  [c a b d])

(defn drop-2-voicings [closed-root-voicing]
  (when (= 4 (count closed-root-voicing))
    (let [invs (inversions closed-root-voicing)
          d2 (map drop-2 invs)]
      (map (fn [bass]
             (first (filter #(= bass (first %)) d2)))
           closed-root-voicing))))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :chord-changed
  (fn [db [_ v]]
    (assoc db :chord v
              :voicings (drop-2-voicings (str/split v #"\s+")))))