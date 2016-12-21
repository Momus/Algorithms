;;http://orgmode.org/worg/org-tutorials/org-publish-html-tutorial.html

(require 'ox-publish)
(setq org-publish-project-alist
      '(

	("org-notes"
	 :base-directory "."
	 :base-extension "org"
	 :publishing-directory "./html"
	 :recursive t
	 :publishing-function org-html-publish-to-html
	 :headline-levels 4
	 :auto-preamble t
	 )

	("org-static"
	 :base-directory "."
	 :base-extension "css\\|js\\|png\\|jpg\\|gif\\|pdf\\|mp3\\|ogg\\|swf"
	 :publishing-directory "./html/"
	 :recursive t
	 :publishing-function org-publish-attachment
	 )

	
	("org"
	 :components ("org-notes" "org-static")
	 )
	
	
	))



;; To publish---
;; Make sure direcotry is mounted:
;; sshfs woland@tilde.town:/home/woland/public_html/algorithms /home/kwaku/repos/learnin/algorithms/html

M-x org-publish-project
