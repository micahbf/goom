(ns goom.synth.patch
  (:require [goom.synth.oscillator :as osc]
            [goom.synth.envelope :as env]
            [reagent.interop :refer [$ $!]]))

(def ^:dynamic *ctx* (js/AudioContext.))

(defn build-amp-envelope []
  (let [gain-node ($ *ctx* createGain)]
    ($! ($ gain-node -gain) -value 0)
    {:fn (env/ad 0.3 1.0)
     :gain-node gain-node}))

(defn build-oscillator []
  (let [square (osc/square *ctx*)]
    ($ square start)
    {:square square}))

(defn build []
  (atom {:amp-envelope (build-amp-envelope)
           :oscillator (build-oscillator)}))

(defn patch-up!
  [{{square-osc :square} :oscillator
    {amp-env-gain :gain-node} :amp-envelope :as synth}]
  ($ square-osc connect amp-env-gain)
  ($ amp-env-gain connect ($ *ctx* -destination))
  synth)

(defn trigger!
  [{{square-osc :square} :oscillator
    {env-fn :fn amp-env-gain :gain-node} :amp-envelope}
   freq]
  (osc/set-freq square-osc freq)
  (env-fn amp-env-gain :gain))
