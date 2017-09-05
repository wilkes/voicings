(ns voicings.views
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))

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
       [chord-input]
       [chord-voicings]])))
