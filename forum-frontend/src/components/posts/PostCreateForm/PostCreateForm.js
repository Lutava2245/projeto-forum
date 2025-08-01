import axios from "axios";
import { Link } from "react-router-dom";
import { useRef } from "react";

export default function PostCreateForm() {

    const title = useRef([]);
    const content = useRef([]);
    const author = useRef([]);

    async function save(event) {
        event.preventDefault();

        const post = {
                title: title.current.value,
                content: content.current.value,
                author: author.current.value
            };

        axios.post(`http://127.0.0.1:8080/api/posts/create`, post)
            .then(response => {
                console.log(response);
                alert('Post criado com sucesso!')
            })
            .catch(error => {
                console.error("Ocorreu um erro ao criar postagem!", error);
                alert('Ocorreu um erro ao criar postagem!')
            })
    }

    return (
        <div>
            <h1>Criar Nova Postagem</h1>
            <form onSubmit={save}>
                <table>
                    <thead><tr><th colSpan="2">Informações da Postagem</th></tr></thead>
                    <tbody>
                        <tr>
                            <td>Título</td>
                            <td><input type="text" ref={title} maxLength="100" required /></td>
                        </tr>
                        <tr>
                            <td>Conteúdo</td>
                            <td><textarea ref={content} maxLength="3000" required /></td>
                        </tr>
                        <tr>
                            <td>Nome do Autor</td>
                            <td><input type="text" ref={author} maxLength="100" required /></td>
                        </tr>
                    </tbody>
                    <tfoot><tr><td colSpan="2"><button type="submit">Enviar</button></td></tr></tfoot>
                </table>
            </form>
            <Link to="/">Voltar para a página inicial</Link>
        </div>
    )
}