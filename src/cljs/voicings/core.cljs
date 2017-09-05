(ns voicings.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [voicings.events]
            [voicings.subs]
            [voicings.views :as views]
            [voicings.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
