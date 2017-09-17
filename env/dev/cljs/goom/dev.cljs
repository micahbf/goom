(ns ^:figwheel-no-load goom.dev
  (:require
    [goom.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install! [:formatters :hints])

(core/init!)
