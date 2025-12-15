@startuml
' PlantUML detallado para Proyecto Iris

package "Clientes" {
    class GmailClient {
        +connect()
        +fetchEmails(): List<Email>
        +disconnect()
    }

    class WhatsAppSender {
        +sendMessage(email: Email)
        +sendBatch(messages: List<Email>)
    }
}

package "Datos" {
    class Email {
        -subject: String
        -sender: String
        -body: String
        -attachments: List
        -timestamp: Date
        +getSummary(): String
        +isDuplicate(): Boolean
    }

    class EmailSection {
        -content: String
        -type: String
    }

    class EmailTreeNode {
        -email: Email
        -left: EmailTreeNode
        -right: EmailTreeNode
    }
}

package "Estructuras" {
    class MessageQueue {
        -queue: Queue<Email>
        +enqueue(email: Email)
        +dequeue(): Email
        +isEmpty(): Boolean
    }

    class EmailStack {
        -stack: Stack<Email>
        +push(email: Email)
        +pop(): Email
        +peek(): Email
    }

    class EmailLinkedList {
        -head: EmailSection
        +add(section: EmailSection)
        +traverse()
    }
}

package "Procesamiento" {
    class EmailProcessor {
        +parseEmail(email: Email): EmailLinkedList
        +checkDuplicate(email: Email): Boolean
        +prepareForWhatsApp(email: Email)
        +handleExStudent(email: Email)
        +logError(error: String)
    }
}

package "ArbolBusqueda" {
    class EmailBST {
        -root: EmailTreeNode
        +insert(email: Email)
        +search(subject: String): Email
        +delete(email: Email)
    }
}

' Relaciones
GmailClient --> MessageQueue : "envía correos"
MessageQueue --> EmailProcessor : "procesa"
EmailProcessor --> WhatsAppSender : "envía mensajes"
EmailProcessor --> EmailLinkedList : "divide en secciones"
EmailProcessor --> EmailBST : "verifica duplicados"
EmailProcessor --> Email : "consulta datos"
EmailProcessor --> EmailStack : "ordena recientes"
EmailProcessor --> "Logs" : "registra errores"
EmailBST --> Email : "nodos contienen"
MessageQueue --> Email : "cola de correos"
EmailStack --> Email : "pila de mensajes"
@enduml