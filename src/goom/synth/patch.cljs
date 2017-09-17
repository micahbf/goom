(ns goom.synth.patch
  (:require [goom.synth.oscillator :as osc]
            [goom.synth.envelope :as env]
            [reagent.interop :refer [$ $!]]))

(def ^:dynamic *ctx* (js/AudioContext.))

(def env-trigger (env/ad 0.3 1.0))
(def square-osc (osc/square *ctx*))
(def env-gain ($ *ctx* createGain))
($ square-osc connect env-gain)
($ square-osc start)
($! ($ env-gain -gain) -value 0)
($ env-gain connect ($ *ctx* -destination))

(defn trigger-env []
  (env-trigger env-gain :gain))

(defn trigger [freq]
  (osc/set-freq square-osc freq)
  (trigger-env))
