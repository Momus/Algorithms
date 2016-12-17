(jdee-project-file-version "1.0")
(jdee-set-variables)
(setq jdee-server-dir ".")
(require 'jdee-checkstyle)
;(setq jdee-checkstyle-classpath '("/home/kwaku/repos/learnin/Algorithms/"))

(require 'pom)
(let ((pom (pom-read-pom)))
  (jde-project-file-version "1.0")
  (jde-set-variables
   '(jde-javadoc-gen-destination-directory "./target/docs/apidocs")
   '(jde-project-name (pom-get-project-id pom))
   '(jde-global-classpath (pom-get-classpath pom))))
