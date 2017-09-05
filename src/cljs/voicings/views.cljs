(ns voicings.views
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))

(defn instructions []
  [:p "Enter a 4 note chord with each note separated by spaces. "
   "For example, a C minor 7 would be entered as: C Eb G Bb"])

(defn chord-input []
  [:div
   [:input {:type "text"
            :value @(rf/subscribe [:chord])
            :on-change #(rf/dispatch [:chord-changed (-> % .-target .-value)])}]])

(defn chord-voicings []
  [:div
   [:h3 "Drop 2 Voicings"]
   [:ul
    (for [v @(rf/subscribe [:voicings])]
      ^{:key v} [:li (str/join " " v)])]])

(defn main-panel []
  (let [chord (rf/subscribe [:chord])]
    (fn []
      [:div
       [instructions]
       [chord-input]
       [chord-voicings]])))
