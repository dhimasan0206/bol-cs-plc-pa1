type expr =
    | CstI of int
    | Var of string
    | Add of expr * expr
    | Mul of expr * expr
    | Sub of expr * expr

let env = [("z", 1);];;

let emptyenv = [];;

let rec lookup env z =
    match env with
    | [] -> failwith (z + "not found")
    | (y, v)::r -> if z=y then v else lookup r z;;

let rec eval e (env : (string * int) list) : int =
    match e with
    | CstI i -> i
    | Var z -> lookup env z
    | Add(e1, e2) -> eval e1 env + eval e2 env
    | Mul(e1, e2) -> eval e1 env * eval e2 env
    | Sub(e1, e2) -> eval e1 env - eval e2 env