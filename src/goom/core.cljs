(ns goom.core
    (:require
     [reagent.core :as r]
     [goom.synth.patch :as s]))


(def synth (s/build))
(s/patch-up! @synth)

;; -------------------------
;; Views

(defn note-button [name freq]
  [:button {:on-click #(s/trigger! @synth freq)} name])

(defn home-page []
  [:div [:div [:h2 "Goom"]]
   [:div
    [note-button "C" 261.63]
    [note-button "D" 293.66]
    [note-button "E" 329.63]
    [note-button "F" 349.23]
    [note-button "G" 392.00]
    [note-button "A" 440.00]
    [note-button "B" 493.88]
    [note-button "C" 523.25]]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
