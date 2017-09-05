(ns voicings.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :chord
  (fn [db]
    (:chord db)))

(re-frame/reg-sub
  :voicings
  (fn [db]
    (:voicings db)))