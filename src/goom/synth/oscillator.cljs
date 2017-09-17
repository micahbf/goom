(ns goom.synth.oscillator
  (:require [reagent.interop :refer [$]]))

(defn osc-node
  [shape ctx]
  (js/OscillatorNode. ctx (clj->js {:shape shape})))

(def sine (partial osc-node :sine))
(def square (partial osc-node :square))
(def saw (partial osc-node :sawtooth))
(def tri (partial osc-node :triangle))

(defn set-freq
  [osc freq]
  (let [ctx ($ osc -context)]
    ($ ($ osc -frequency) setValueAtTime freq ($ ctx -currentTime))))
