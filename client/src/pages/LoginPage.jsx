import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { Button } from '@/components/ui/button';
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { useState } from 'react';
import { api } from '@/lib/axios';

// Esquema de validação para o formulário de login usando zod
const formSchema = z.object({
    username: z.string().min(4, {
        message: 'O nome de usuário deve ter no mínimo 4 caracteres.',
    }),
    password: z.string().min(8, {
        message: 'A senha deve ter no mínimo 8 caracteres.',
    }),
});

function Login() {
    const [loginError, setLoginError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    // 1. Defina o seu formulário.
    const form = useForm({
        resolver: zodResolver(formSchema),
        defaultValues: {
            username: '',
            password: '',
        },
    });

    // 2. Defina o manipulador de submissão.
    async function onSubmit(values) {
        setLoginError('');
        setSuccessMessage('');
        try {
            // Usando Axios para a requisição POST
            const response = await api.post('/login', {
                username: values.username,
                password: values.password,
            });

            // salvar o token no localStorage
            setSuccessMessage('Login bem-sucedido!');
            console.log('Token JWT:', response.data.token);
            localStorage.setItem('authToken', response.data.token);

        } catch (error) {
            if (error.response) {
                // A requisição foi feita e o servidor respondeu com um código de status de erro
                const errorMessage = error.response.data.message || 'Erro ao tentar fazer login.';
                setLoginError(errorMessage);
                console.error('Login falhou:', errorMessage);
            } else if (error.request) {
                // A requisição foi feita, mas não houve resposta do servidor
                setLoginError('Não foi possível conectar ao servidor. Verifique se o servidor está rodando.');
                console.error('Erro de rede:', error.request);
            } else {
                // Algo aconteceu na configuração da requisição que gerou o erro
                setLoginError('Erro inesperado durante a requisição.');
                console.error('Erro:', error.message);
            }
        }
    }

    return (
        <div className="flex justify-center items-center h-screen">
            <div className="w-full max-w-md p-8 space-y-6 bg-white rounded shadow-md">
                <h2 className="text-2xl font-bold text-center">Login</h2>
                <Form {...form}>
                    <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                        <FormField
                            control={form.control}
                            name="username"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Nome de Usuário</FormLabel>
                                    <FormControl>
                                        <Input placeholder="usuário" {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        <FormField
                            control={form.control}
                            name="password"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Senha</FormLabel>
                                    <FormControl>
                                        <Input type="password" placeholder="senha" {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        {loginError && (
                            <p className="text-sm text-red-500">{loginError}</p>
                        )}
                        {successMessage && (
                            <p className="text-sm text-green-500">{successMessage}</p>
                        )}
                        <Button type="submit" className="w-full">
                            Entrar
                        </Button>
                    </form>
                </Form>
            </div>
        </div>
    );
}

export default Login;