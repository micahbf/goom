(ns goom.synth.envelope
  (:require [reagent.interop :refer [$]]))

(defn ad
  [attack decay]
  (fn [node param-name]
    (let [param (aget node (clj->js param-name))
          ctx   ($ node -context)
          now   ($ ctx -currentTime)]
      ($ param cancelScheduledValues now)
      ($ param exponentialRampToValueAtTime 1 (+ now attack))
      ($ param exponentialRampToValueAtTime 0.0000001 (+ now attack decay)))))
