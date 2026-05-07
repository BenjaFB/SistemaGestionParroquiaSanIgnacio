import tseslint from 'typescript-eslint'
import builderbot from 'eslint-plugin-builderbot'

export default [
    {
        ignores: ['dist/**', 'node_modules/**', 'rollup.config.js'],
    },
    ...tseslint.configs.recommended,
    {
        plugins: {
            builderbot,
        },
        languageOptions: {
            ecmaVersion: 'latest',
            sourceType: 'module',
        },
        rules: {
            ...builderbot.configs.recommended.rules,
            '@typescript-eslint/no-explicit-any': 'off',
            '@typescript-eslint/no-floating-promises': 'off',
            '@typescript-eslint/no-unsafe-argument': 'off',
            '@typescript-eslint/no-unsafe-assignment': 'off',
            '@typescript-eslint/no-unsafe-call': 'off',
            '@typescript-eslint/no-unsafe-member-access': 'off',
            '@typescript-eslint/no-unsafe-return': 'off',
            "@typescript-eslint/no-explicit-any": "off",
            "@typescript-eslint/ban-ts-comment": "off"
        },
    },
]
