;  MIT License

;  Copyright guenchi (c) 2018 
         
;  Permission is hereby granted, free of charge, to any person obtaining a copy
;  of this software and associated documentation files (the "Software"), to deal
;  in the Software without restriction, including without limitation the rights
;  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;  copies of the Software, and to permit persons to whom the Software is
;  furnished to do so, subject to the following conditions:
         
;  The above copyright notice and this permission notice shall be included in all
;  copies or substantial portions of the Software.
         
;  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;  SOFTWARE.






(library (fli pycall)
    (export
        py-sigle-input
        py-file-input
        py-eval-input

        py-initialize
        py-finalize
        py-new-interpreter
        py-end-interpreter

        py/int-from-long

        py/long-from-long
        py/long-from-unsigned-long
        py/long-from-longlong
        py/long-from-unsigned-longlong
        py/long-from-double
        py/long-from-size_t
        py/long-from-ssize_t
        py/long-as-long
        py/long-as-unsigned-long
        py/long-as-longlong
        py/long-as-unsigned-longlong
        py/long-as-double
        py/long-as-ssize_t

        py/float-from-double
        py/float-as-double
        
        py/string-from-string

        py/tuple-new
        py/tuple-set-item!
        py/tuple-get-item

        py/run-simple-file
        py/run-file
        py/run-file-exflags
        py/run-simple-string
        py/run-string
        py/run-string-flags

        py/import-import
        py/import-import-module
        py/import-exec-code-module

        py/module-new
        py/module-get-dict
        py/module-get-name
        py/module-get-filename
        
        py/object-get-attr-string
        py/object-call-object
    
        py-compile-string

        py-import
        py-from
        py-define)
    (import
        (scheme))

(define lib (load-shared-object "py.so"))

(define py-sigle-input 256)
(define py-file-input 257)
(define py-eval-input 258)


(define py-initialize
    (foreign-procedure "Py_Initialize" () void))

(define py-finalize
    (foreign-procedure "Py_Finalize" () void))

(define py-new-interpreter
    (foreign-procedure "Py_NewInterpreter" () uptr))

(define py-end-interpreter
    (foreign-procedure "Py_EndInterpreter" (uptr) void))

(define py/int-from-long
    (foreign-procedure "PyInt_FromLong" (long) uptr))

(define py/long-from-long
    (foreign-procedure "PyLong_FromLong" (long) uptr))

(define py/long-from-unsigned-long
    (foreign-procedure "PyLong_FromUnsignedLong" (unsigned-long) uptr))

(define py/long-from-longlong
    (foreign-procedure "PyLong_FromLongLong" (long-long) uptr))

(define py/long-from-unsigned-longlong
    (foreign-procedure "PyLong_FromUnsignedLongLong" (unsigned-long-long) uptr))

(define py/long-from-double
    (foreign-procedure "PyLong_FromDouble" (double) uptr))

(define py/long-from-size_t
    (foreign-procedure "PyLong_FromSize_t" (size_t) uptr))

(define py/long-from-ssize_t
    (foreign-procedure "PyLong_FromSsize_t" (ssize_t) uptr))

(define py/long-as-long
    (foreign-procedure "PyLong_AsLong" (uptr) long))

(define py/long-as-unsigned-long
    (foreign-procedure "PyLong_AsUnsignedLong" (uptr) unsigned-long))

(define py/long-as-longlong
    (foreign-procedure "PyLong_AsLongLong" (uptr) long-long))

(define py/long-as-unsigned-longlong
    (foreign-procedure "PyLong_AsUnsignedLongLong" (uptr) unsigned-long-long))

(define py/long-as-double
    (foreign-procedure "PyLong_AsDouble" (uptr) double))

(define py/long-as-ssize_t
    (foreign-procedure "PyLong_AsSsize_t" (uptr) ssize_t))

(define py/float-from-double
    (foreign-procedure "PyFloat_FromDouble" (double) uptr))
    
(define py/float-as-double
    (foreign-procedure "PyFloat_AsDouble" (uptr) double))

(define py/string-from-string
    (foreign-procedure "PyString_FromString" (string) uptr))

(define py/tuple-new
    (foreign-procedure "PyTuple_New" (int) uptr))

(define py/tuple-set-item!
    (foreign-procedure "PyTuple_SetItem" (uptr int uptr) void))

(define py/tuple-get-item
    (foreign-procedure "PyTuple_GetItem" (uptr int) uptr))

(define py/run-simple-file
    (foreign-procedure "PyRun_SimpleFile" (uptr string) int))

(define py/run-file
    (foreign-procedure "PyRun_File" (uptr string int uptr uptr) uptr))

(define py/run-file-exflags
    (foreign-procedure "PyRun_FileExFlags" (uptr string int uptr uptr int uptr) uptr))

(define py/run-simple-string
    (foreign-procedure "PyRun_SimpleString" (string) int))

(define py/run-string
    (foreign-procedure "PyRun_String" (string int uptr uptr) uptr))

(define py/run-string-flags
    (foreign-procedure "PyRun_StringFlags" (string int uptr uptr uptr) uptr))
    
(define py/import-import
    (foreign-procedure "PyImport_Import" (uptr) uptr))

(define py/import-import-module
    (foreign-procedure "PyImport_ImportModule" (string) uptr))

(define py/import-exec-code-module
    (foreign-procedure "PyImport_ExecCodeModule" (string uptr) uptr))

(define py/module-new
    (foreign-procedure "PyModule_New" (string) uptr))

(define py/module-get-dict
    (foreign-procedure "PyModule_GetDict" (uptr) uptr))

(define py/module-get-name
    (foreign-procedure "PyModule_GetName" (uptr) string))

(define py/module-get-filename
    (foreign-procedure "PyModule_GetFileName" (uptr) string))

(define py/module-clear
    (foreign-procedure "PyModule_Clear" (uptr) void))

(define py/object-get-attr-string
    (foreign-procedure "PyObject_GetAttrString" (uptr string) uptr))

(define py/object-call-object
    (foreign-procedure "PyObject_CallObject" (uptr uptr) uptr))

(define py-compile-string
    (foreign-procedure "Py_CompileString" (string string int) uptr))


(define-syntax py-import
    (syntax-rules (as)
        ((_ e)
            (py/run-simple-string (string-append "import " (symbol->string e))))
        ((_ e as k)
            (py/run-simple-string (string-append "import " (symbol->string e) " as " (symbol->string k))))))

(define-syntax py-from
    (syntax-rules (import)
        ((_ e import k)
            (py/run-simple-string (string-append "from " (symbol->string e) " import " (symbol->string k))))))


(define any->string
    (lambda (a)
        (cond
            ((number? a) (number->string a))
            ((symbol? a) (symbol->string a))
            ((string? a) a)
            (else (display "input must be number, symbol or string")))))            

(define py-define 
    (lambda (x y)
        (py/run-simple-string (string-append (symbol->string x) " = " (any->string y)))))


(define py-decorater
    (lambda (x)
        (py/run-simple-string (string-append  "@" (symbol->string x)))))

(define list->*tuple
    (lambda (lst)
        (define len (length lst))
        (define *p (py/tuple-new len))
        (let loop ((n 0)(lst lst))
            (if (< n len)
                (begin
                    (py/tuple-set-item! *p n (py/int-from-long (car lst)))
                    (loop (+ n 1) (cdr lst)))
                *p))))




)