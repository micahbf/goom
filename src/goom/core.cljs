(ns goom.core
    (:require
     [reagent.core :as r]
     [goom.synth.oscillator :as osc]))

;; -------------------------
;; Views

(def current-freq (r/atom 440))

(defn note-button [name freq]
  [:button {:on-click #(reset! current-freq freq)} name])

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
    [note-button "C" 523.25]]
   [:h5 @current-freq]])

(def ctx (js/AudioContext.))
(def square-osc (osc/square ctx))
(.connect square-osc (.-destination ctx))
(.start square-osc)

(r/track! #(osc/set-freq square-osc @current-freq))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
