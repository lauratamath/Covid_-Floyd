#Laura Tamath
#Carné no. 19365
#Hoja 10: algoritmo de Floyd
#Analiza la ruta más corta para llegar a un hospital de Guatemala

import networkx as nx

def addNodes(node):
    if node not in allNodos:
        G.add_node(node)
        D.add_node(node)

def addEdge(node1,node2,weight1):
    G.add_edge(node1,node2,weight=weight1)
    D.add_edge(node1,node2,weight=weight1)

def eliminateEdge(node1,node2):
    G.remove_edge(node1,node2)
    D.remove_edge(node1,node2)

def buscarRuta(node1,node2):
    try:
        floyd=nx.fw_predecessor_distance(D,None) 
        city1=floyd[0][node1][node2]
        route=[]
        route.append(city1) 
        while city1!=node1:
            newCity=floyd[0][node1][city1] 
            route.append(newCity) 
            city1=newCity 
        count=0
        tope=len(route)-1
        orderRoute=[]
        while count < len(route)-1:
            orderRoute.append(route[tope-1]) #No incluye el ultimo porque es la ciudad de inicio
            tope-=1
            count+=1
        if len(orderRoute)!=0:
            print ("La route mas corta desde "+node1+" hasta "+node2+" es ir por: ")
            print (orderRoute)
            print ('\n')
        else:
            print ("La route mas corta es una route directa\n")
    except Exception:
        print ("No hay routes posibles\n")
    

#se valida si es un número
def Validate(texto):
    si=True
    try:
        float(texto)
    except:
        si=False
    return si

#se halla el centro del grafo
G = nx.Graph() 
D = nx.DiGraph()
allArrays=[]
archivo = open("guategrafo.txt","r")
for line in archivo:
    array=line.split()
    allNodos=G.nodes()
    allArrays.append(array)
    addNodes(array[0])
    addNodes(array[1])
    addEdge(array[0],array[1],array[2])

#Se inicia el menú para que el usuario escoja qué desea hacer
ciclo=0
while ciclo==0:
    print("Acontinuacion se mostraran opciones, por favor ingrese el numero de la opcion a realizar")
    opcion= input("Que desea hacer?\n>>1. Mostrar la ruta mas corta de una ciudad a otra\n>>2. Encontrar el centro del grafo\n>>3. Indicar de una interrupcion\n>>4. Establecer una conexion entre ciudades\n>>5. Salir \n>")
    if opcion=="1":
        origen=input("Ingrese el nombre de la ciudad de Inicio, sin espacios: ")
        while origen not in allNodos:
            origen=input("Esta ciudad no se halla previamente ingresada. Ingrese otra: ")
        destino=input("Ingrese el nombre de la ciudad a donde desea llegar, sin espacios: ")
        while destino not in allNodos:
            destino=input("Esta ciudad no se halla previamente ingresada. Ingrese otra: ")
        buscarRuta(origen,destino)
        ciclo=0
    if opcion=="2":
        try:
            print (nx.center(G,None))
        except Exception:
            print ("El centro del grafo no existe")
        ciclo=0
    if opcion=="3":
        try:
            print("--------------------------------------------------------------------")
            print("Bienvenido al programa que lo ayudara a encontrar la ruta mas corta entre hospitales")
            origen=input("Ingrese el nombre del departamento de origen: ")
            destino=input("Ingrese el nombre del departamento de destino: ")
            eliminateEdge(origen,destino)
            print ("Conexion eliminada")
            print (D.edges)
        except Exception:
            print ("Error al ingresar, verifique correctamente")
        ciclo=0
    if opcion=="4":
        origen=input("Ingrese el nombre del departamento de origen: ")
        destino=input("Ingrese el nombre del departamento de destino: ")
        km=input("Ingrese la cantidad de km entre ambas ciudades: ")
        while not Validate(km):
            km=input("Error. Por favor ingresar con numeros los kilometros: ")
        addNodes(origen)
        addNodes(destino)
        addEdge(origen,destino,km)
        ciclo=0
    if opcion=="5":
        print ("Saliendo")
        ciclo=1
